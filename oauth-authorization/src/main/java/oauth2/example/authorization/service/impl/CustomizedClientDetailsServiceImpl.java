package oauth2.example.authorization.service.impl;

import oauth2.example.authorization.entity.OAuth2ClientDetails;
import oauth2.example.authorization.repository.OAuth2ClientDetailsRepository;
import oauth2.example.authorization.security.service.CustomizedClientDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>创建时间: 2021/1/30 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class CustomizedClientDetailsServiceImpl implements CustomizedClientDetailsService {

    private JsonMapper mapper = createJsonMapper();
    private static final Log logger = LogFactory.getLog(CustomizedClientDetailsServiceImpl.class);
    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
    private OAuth2ClientDetailsRepository repository;

    /**
     * @param passwordEncoder the password encoder to set
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CustomizedClientDetailsServiceImpl(OAuth2ClientDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuth2ClientDetails details = repository.selectById(clientId);

        if (details == null) {
            throw new ClientRegistrationException(String.format("无效的 %s", clientId));
        }

        BaseClientDetails baseClientDetails = new BaseClientDetails(
                details.getClientId(),
                details.getResourceIds(),
                details.getScope(),
                details.getAuthorizedGrantTypes(),
                details.getAuthorities(),
                details.getWebServerRedirectUri()
        );
        baseClientDetails.setClientSecret(details.getClientSecret());
        if (details.getAccessTokenValidity() != null) {
            baseClientDetails.setAccessTokenValiditySeconds(details.getAccessTokenValidity());
        }
        if (details.getRefreshTokenValidity() != null) {
            baseClientDetails.setRefreshTokenValiditySeconds(details.getRefreshTokenValidity());
        }
        try {
            Map<String, Object> additionalInformation = mapper.read(details.getAdditionalInformation(), Map.class);
            baseClientDetails.setAdditionalInformation(additionalInformation);
        } catch (Exception e) {
            logger.warn("Could not decode JSON for additional information: " + details, e);
        }

        if (details.getScope() != null) {
            baseClientDetails.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(details.getScope()));
        }

        return baseClientDetails;
    }

    @Override
    public boolean addClientDetails(ClientDetails clientDetails) {
        OAuth2ClientDetails oauth2ClientDetails = convertFields(clientDetails);
        int result = repository.insert(oauth2ClientDetails);
        return result == 1;
    }

    @Override
    public boolean updateClientDetails(ClientDetails clientDetails) {
        return false;
    }

    @Override
    public boolean deleteByClientId(String clientId) {
        return false;
    }

    private OAuth2ClientDetails convertFields(ClientDetails clientDetails) {
        OAuth2ClientDetails details = new OAuth2ClientDetails();

        String json = null;
        try {
            json = mapper.write(clientDetails.getAdditionalInformation());
        } catch (Exception e) {
            logger.warn("Could not serialize additional information: " + clientDetails, e);
        }

        String clientSecret = clientDetails.getClientSecret() != null ? passwordEncoder.encode(clientDetails.getClientSecret())
                : null;

        details.setClientId(clientDetails.getClientId());
        details.setClientSecret(clientSecret);
        details.setAutoapprove(getAutoApproveScopes(clientDetails));

        details.setResourceIds(clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getResourceIds()) : null);

        details.setScope(clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getScope()) : null);

        details.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes() != null ? StringUtils
                .collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null);

        details.setWebServerRedirectUri(clientDetails.getRegisteredRedirectUri() != null ? StringUtils
                .collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null);

        details.setAuthorities(clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails
                .getAuthorities()) : null);

        details.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
        details.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
        details.setAdditionalInformation(json);

        return details;
    }

    private String getAutoApproveScopes(ClientDetails clientDetails) {
        if (clientDetails.isAutoApprove("true")) {
            return "true"; // all scopes autoapproved
        }
        Set<String> scopes = new HashSet<String>();
        for (String scope : clientDetails.getScope()) {
            if (clientDetails.isAutoApprove(scope)) {
                scopes.add(scope);
            }
        }
        return StringUtils.collectionToCommaDelimitedString(scopes);
    }

    interface JsonMapper {
        String write(Object input) throws Exception;

        <T> T read(String input, Class<T> type) throws Exception;
    }

    private static JsonMapper createJsonMapper() {
        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", null)) {
            return new Jackson2Mapper();
        }
        return new NotSupportedJsonMapper();
    }

    private static class Jackson2Mapper implements JsonMapper {
        private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

        @Override
        public String write(Object input) throws Exception {
            return mapper.writeValueAsString(input);
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            return mapper.readValue(input, type);
        }
    }

    private static class NotSupportedJsonMapper implements JsonMapper {
        @Override
        public String write(Object input) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }

        @Override
        public <T> T read(String input, Class<T> type) throws Exception {
            throw new UnsupportedOperationException(
                    "Neither Jackson 1 nor 2 is available so JSON conversion cannot be done");
        }
    }

}

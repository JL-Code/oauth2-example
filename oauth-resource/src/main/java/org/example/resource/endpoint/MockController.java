package org.example.resource.endpoint;

import org.example.resource.vo.AppVO;
import org.example.resource.vo.MenuVO;
import org.example.resource.vo.StationVO;
import org.example.resource.vo.UserVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: 登录控制器 </p>
 * <p>创建时间: 2020/6/20 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class MockController {
//    private RestOperations restTemplate;
//    private final String tokenEndpoint = "http://localhost:8080/oauth/token?username=250577914&password=123&grant_type=password&scope=get_user_info&client_id=cdb&client_secret=cdb";
//    private final String clientId = "cdb";
//    private final String clientSecret = "cdb";
//
//    public MockController() {
//        this.restTemplate = new RestTemplate();
//        ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
//            @Override
//            // Ignore 400
//            public void handleError(ClientHttpResponse response) throws IOException {
//                if (response.getRawStatusCode() != 400) {
//                    super.handleError(response);
//                }
//            }
//        });
//        ;
//    }
//
//    @PostMapping("/api/oauth/token")
//    public OAuth2AccessToken login(String username, String password) {
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("username", username);
//        body.add("password", password);
//        body.add("clientId", clientId);
//        body.add("clientSecret", clientSecret);
//        body.add("grant_type", "password");
//        body.add("scope", "get_token");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        ResponseEntity<OAuth2AccessToken> response = restTemplate.postForEntity(tokenEndpoint,
//                new HttpEntity<>(body, headers),
//                OAuth2AccessToken.class);
//
//        return response.getBody();
//    }
//
//    @GetMapping("/api/app/apps")
//    public List<AppVO> listApps() {
//        List<AppVO> apps = new ArrayList<>();
//        AppVO app = new AppVO();
//        app.setSelected(true);
//        app.setSystemId("123");
//        app.setSystemCode("org");
//        app.setSystemName("组织管理");
//        apps.add(app);
//        return apps;
//    }
//
//    @GetMapping("/api/app/menus")
//    public List<MenuVO> getAppMenus(String appId) {
//        List<MenuVO> menus = new ArrayList<>();
//        MenuVO menu = new MenuVO();
//        menu.setFunctionType(1);
//        menu.setUri("/org/organization-user/organization");
//        menu.setId("123");
//        menu.setName("组织架构");
//        menu.setPath("/org/organization-user/organization");
//        menu.setNewWindow(false);
//        menu.setType("function");
//        menu.setChildren(new ArrayList<>());
//        menus.add(menu);
//        return menus;
//    }
//
//    @GetMapping("/api/user/info")
//    public UserVO getUserInfo() {
//        List<StationVO> stations = new ArrayList<>();
//
//        StationVO station = new StationVO();
//        station.setCompanyName("衡泽软件");
//        station.setDepartmentName("开发部");
//        station.setStationName("开发工程师");
//
//        stations.add(station);
//
//        UserVO user = new UserVO();
//        user.setUserId("123");
//        user.setUserName("jiangy");
//        user.setUserCode("jiangy");
//        user.setStations(stations);
//
//        return user;
//    }

}

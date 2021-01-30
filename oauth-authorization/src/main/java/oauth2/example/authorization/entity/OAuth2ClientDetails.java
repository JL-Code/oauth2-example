package oauth2.example.authorization.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>创建时间: 2021/1/30 </p>
 * oauth_client_details table sql
 * * ```sql
 * client_id VARCHAR(256) PRIMARY KEY,
 * resource_ids VARCHAR(256),
 * client_secret VARCHAR(256),
 * scope VARCHAR(256),
 * authorized_grant_types VARCHAR(256),
 * web_server_redirect_uri VARCHAR(256),
 * authorities VARCHAR(256),
 * access_token_validity INTEGER,
 * refresh_token_validity INTEGER,
 * additional_information VARCHAR(4096),
 * autoapprove VARCHAR(256)
 * ```
 * https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 * https://andaily.com/spring-oauth-server/db_table_description.html
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@TableName("oauth_client_details")
public class OAuth2ClientDetails {

    @TableId("client_id")
    private String clientId;
    /**
     * 客户端所能访问的资源id集合,多个资源时用逗号(,)分隔。
     * 如: "unity-resource,mobile-resource".
     */
    private String resourceIds;
    /**
     * 用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成).
     * 对于不同的grant_type,该字段都是必须的.
     * 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.
     */
    private String clientSecret;
    /**
     * 指定客户端申请的权限范围,可选值包括read,write,trust;
     * 若有多个权限范围用逗号(,)分隔,如: "read,write".
     */
    private String scope;
    /**
     * 指定客户端支持的grant_type,可选值包括 authorization_code,refresh_token,password,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如:
     * "authorization_code,password".
     * 在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有: "authorization_code,refresh_token"(针对通过浏览器访问的客户端); "password,refresh_token"(针对移动设备的客户端).
     * implicit与client_credentials在实际中很少使用.
     */
    private String authorizedGrantTypes = "authorization_code,refresh_token";
    /**
     * 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致. 下面分别说明:
     * 当grant_type=authorization_code时, 第一步 从 spring-oauth-server获取 'code'时客户端发起请求时必须有redirect_uri参数, 该参数的值必须与 web_server_redirect_uri的值一致. 第二步 用 'code' 换取 'access_token' 时客户也必须传递相同的redirect_uri.
     * 在实际应用中, web_server_redirect_uri在注册时是必须填写的, 一般用来处理服务器返回的code, 验证state是否合法与通过code去换取access_token值.
     * 在spring-oauth-client项目中, 可具体参考 AuthorizationCodeController.java 中的 authorizationCodeCallback方法.
     * 当 grant_type=implicit 时通过 redirect_uri 的 hash值来传递access_token值.如:
     * http://localhost:7777/spring-oauth-client/implicit#access_token=dc891f4a-ac88-4ba6-8224-a2497e013865&token_type=bearer&expires_in=43199
     * 然后客户端通过JS等从hash值中取到access_token值.
     */
    private String webServerRedirectUri;
    /**
     * 指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".
     * 对于是否要设置该字段的值,要根据不同的grant_type来判断, 若客户端在Oauth流程中需要用户的用户名(username)与密码(password)的(authorization_code,password),
     * 则该字段可以不需要设置值,因为服务端将根据用户在服务端所拥有的权限来判断是否有权限访问对应的API.
     * 但如果客户端在Oauth流程中不需要用户信息的(implicit,client_credentials),
     * 则该字段必须要设置对应的权限值, 因为服务端将根据该字段值的权限来判断是否有权限访问对应的API.
     */
    private String authorities;
    /**
     * 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
     * 在服务端获取的access_token JSON数据中的expires_in字段的值即为当前access_token的有效时间值.
     * 在项目中, 可具体参考DefaultTokenServices.java中属性accessTokenValiditySeconds.
     * 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.
     */
    private Integer accessTokenValidity;
    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
     * 若客户端的grant_type不包括refresh_token,则不用关心该字段 在项目中, 可具体参考DefaultTokenServices.java中属性refreshTokenValiditySeconds.
     * <p>
     * 在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.
     */
    private Integer refreshTokenValidity;
    /**
     * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
     * {"country":"CN","country_code":"086"}
     * 按照spring-security-oauth项目中对该字段的描述
     * Additional information for this client, not need by the vanilla OAuth protocol but might be useful, for example,for storing descriptive information.
     * (详见ClientDetails.java的getAdditionalInformation()方法的注释)
     * 在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等.
     */
    private String additionalInformation;
    /**
     * 设置用户是否自动 Approval 操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'.
     * 该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权.
     * spring-security-oauth2 2.0 版本后添加的新属性.
     */
    private String autoapprove;
}

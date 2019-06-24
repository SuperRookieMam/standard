package com.standard.resource.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import com.standard.oauthCommon.entity.MClientDetails;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@IsCreate
@Entity
@Table(name = "oauth_client_details_")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OauthClientDetails extends BaseEntity implements MClientDetails{
    private static final long serialVersionUID = -6124668003677932153L;

    @Id
    @Column(name = "client_id_")
    @GeneratedValue(generator = "jpa-uuid")
    private String clientId;

    /**
     * 客户端能访问的资源id集合
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "resource_id_")
    @CollectionTable(name = "resource_server_client_",
            joinColumns = {@JoinColumn(name = "client_id_", referencedColumnName = "client_id_")})
    private Set<String> resourceIds = Collections.emptySet();

    @Column(name = "secret_required_")
    private boolean secretRequired = true;

    @Column(name = "client_secret_")
    private String clientSecret;

    /**
     * 此客户端对于某个接口的访问权限
     *  指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,
     *  如: "read,write". scope的值与security.xml中配置的‹intercept-url的access属性有关系.
     *  如‹intercept-url的配置为  ‹intercept-url pattern="/m/**" access="ROLE_MOBILE,SCOPE_READ"/>
     *  则说明访问该URL时的客户端必须有read权限范围. write的配置值为SCOPE_WRITE,
     *  trust的配置值为SCOPE_TRUST.  在实际应该中, 该值一般由服务端指定, 常用的值为read,write.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "scope_")
    @CollectionTable(name = "client_scope_",
            joinColumns = { @JoinColumn(name = "client_id_", referencedColumnName = "client_id_")})
    private Set<String> scope = new HashSet<>();


    /**
     * 指定客户端支持的grant_type,
     * 可选值包括authorization_code,password,refresh_token,implicit,client_credentials,
     * 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password". 
     * 在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有:
     * "authorization_code,refresh_token"(针对通过浏览器访问的客户端);
     * "password,refresh_token"(针对移动设备的客户端).  implicit与client_credentials在实际中很少使用.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "grant_type_")
    @CollectionTable(name = "authorized_grant_type_", joinColumns = {
            @JoinColumn(name = "client_id_", referencedColumnName = "client_id_")
    })
    private Set<String> authorizedGrantTypes = new HashSet<>();
    /**
     * 客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时,
     * 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.
     * 下面分别说明:
     * 1. 当grant_type=authorization_code时,
     * 第一步 从 spring-oauth-server获取 'code'时客户端发起请求时必须有redirect_uri参数,
     * 该参数的值必须与web_server_redirect_uri的值一致.
     * 第二步 用 'code' 换取 'access_token' 时客户也必须传递相同的redirect_uri 在实际应用中,
     *  web_server_redirect_uri在注册时是必须填写的, 一般用来处理服务器返回的code,
     * 验证state是否合法与通过code去换取access_token值. 
     * 2.在spring-oauth-client项目中, 可具体参考AuthorizationCodeController.java
     * 中的authorizationCodeCallback方法. 当grant_type=implicit时通过redirect_uri的hash值来传递access_token值.如:
     * http://localhost:7777/spring-oauth-client/implicit#
     * access_token=dc891f4a-ac88-4ba6-8224-a2497e013865&token_type=bearer&expires_in=43199
     * 然后客户端通过JS等从hash值中取到access_token值.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "redirect_uri_")
    @CollectionTable(name = "client_registered_redirect_uri_",
            joinColumns = {@JoinColumn(name = "client_id_", referencedColumnName = "client_id_")})
    private Set<String> registeredRedirectUri =new HashSet<>();

    @Transient
    private Collection<GrantedAuthority> authorities =new ArrayList<>();

    @Transient
    private Map<String, Object> additionalInformation =new HashMap<>();

    /**
     * 设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
     */
    @Column(name = "access_token_validity_seconds_")
    private Integer accessTokenValiditySeconds = 60 * 60 * 24*365;

    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
     */
    @Column(name = "refresh_token_validity_seconds_")
    private Integer refreshTokenValiditySeconds = 60 * 60 * 24 * 365;

    /**
     * 设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'. 
     * 该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,
     * 若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权.
     * 该字段与 trusted 有类似的功能, 是 spring-security-oauth2 的 2.0 版本后添加的新属性.
     */
    @Column(name = "auto_approve_")
    private boolean autoApprove = false;

    @Override
    public boolean isScoped() {
        return true;
    }
    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

}

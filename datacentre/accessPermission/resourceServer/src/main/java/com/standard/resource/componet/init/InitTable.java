package com.standard.resource.componet.init;

import com.standard.resource.entity.*;
import com.standard.resource.service.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Conditional(InitCondition.class)
public class InitTable  implements InitializingBean {
    @Autowired
    AuthorizedGrantTypeService authorizedGrantTypeService;
    @Autowired
    ClientRegisteredRedirectUriService clientRegisteredRedirectUriService;
     @Autowired
    ClientScopeService clientScopeService;
    @Autowired
    OAthGrantedAuthorityService oAthGrantedAuthorityService ;
    @Autowired
    OAthUserDetailesService oAthUserDetailesService;
    @Autowired
    OAuthAccessTokenService oAuthAccessTokenService;
    @Autowired
    OAuthClientDetailsService oAuthClientDetailsService;
//    @Autowired
//    OAuthCodeDao oAuthCodeDao;
    @Autowired
    OAuthRefreshTokenService oAuthRefreshTokenService;
    @Autowired
    ResourceServerClientService resourceServerClientService;
    @Autowired
    ResourceServerService resourceServerService;
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    UserApprovalService userApprovalService;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public void afterPropertiesSet() throws Exception {
    }
    @Transactional(value = "jpaTransactionManager")
    public  void  init(){
        RoleInfo roleInfo =new RoleInfo();
        roleInfo.setRolName("世界最帅美男");
        roleInfoService.insertByEntity(roleInfo);

        UserRole userRole =new UserRole();
        userRole.setRoleId(roleInfo.getId());
        userRole.setUserName("绝世好男人");
        userRoleService.insertByEntity(userRole);

        OAthUserDetailes user =new OAthUserDetailes();
        user.setUsername("绝世好男人");
        user.setPassword("$2a$10$hjI6o5xdOxaxGnqNaFzwwOUnXEvUOAASQMKXPNZ5W9o18skQBwcS6");
        user.setCredentials("$2a$10$hjI6o5xdOxaxGnqNaFzwwOUnXEvUOAASQMKXPNZ5W9o18skQBwcS6");
        user.setEnabled(true);
        user.setLock(false);
        user.setExpired(false);
        user.setHeadImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3715031121,1461812215&fm=26&gp=0.jpg");
        oAthUserDetailesService.insertByEntity(user);

        OauthClientDetails clientDetails =new OauthClientDetails();
        clientDetails.setAccessTokenValiditySeconds(60000);
        clientDetails.setAutoApprove(true);
        clientDetails.setClientSecret("$2a$10$hjI6o5xdOxaxGnqNaFzwwOUnXEvUOAASQMKXPNZ5W9o18skQBwcS6");
        clientDetails.setRefreshTokenValiditySeconds(60000);
        clientDetails.setSecretRequired(true);
        oAuthClientDetailsService.insertByEntity(clientDetails);

        ResourceServer resourceServer =new ResourceServer();
        resourceServer.setName("zuul");
        resourceServer.setRegisterUrl("http://localhost:8082");
        resourceServer.setSecret("123456");
        resourceServer.setUse(true);
        resourceServerService.insertByEntity(resourceServer);

        ResourceServerClient resourceServerClient =new ResourceServerClient();
        resourceServerClient.setClientId(clientDetails.getClientId());
        resourceServerClient.setResourceId(resourceServer.getId());
        resourceServerClientService.insertByEntity(resourceServerClient);

        OAthGrantedAuthority oAthGrantedAuthority =new OAthGrantedAuthority();
        oAthGrantedAuthority.setApiDescription("这是什么");
        oAthGrantedAuthority.setApiName("张氏");
        oAthGrantedAuthority.setApiUri("/oauht/tokenGet");
        oAthGrantedAuthority.setMethod(HttpMethod.GET);
        oAthGrantedAuthority.setRoleId(userRole.getRoleId()+"");
        oAthGrantedAuthority.setMactherType("url");
        oAthGrantedAuthority.setResourceId(resourceServer.getId());
        oAthGrantedAuthorityService.insertByEntity(oAthGrantedAuthority);

        AuthorizedGrantType authorizedGrantType =new AuthorizedGrantType();
        authorizedGrantType.setClientId(clientDetails.getClientId());
        authorizedGrantType.setGrantType("authorization_code");
        authorizedGrantTypeService.insertByEntity(authorizedGrantType);

        ClientRegisteredRedirectUri uri =new ClientRegisteredRedirectUri();
        uri.setClientId(clientDetails.getClientId());
        uri.setRedirectUri("http://localhost:8082");
        clientRegisteredRedirectUriService.insertByEntity(uri);

        ClientScope clientScope =new ClientScope();
        clientScope.setClientId(clientDetails.getClientId());
        clientScope.setScope("read");
        clientScope.setResourceId(resourceServer.getId());
        clientScopeService.insertByEntity(clientScope);
    }
}

package com.standard.permission.componet.config;


import com.standard.permission.componet.constpackage.ConstParam;
import com.standard.permission.componet.feature.DefaultTokenServicesCover;
import com.standard.permission.componet.feature.TokenStoreCover;
import com.standard.permission.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

@Configuration
@EnableAuthorizationServer
public class OAuthServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private OAuthClientDetailsService oauthClientDetailsService;
    @Autowired
    private UserApprovalService userApprovalService;
    @Autowired
    private OAthUserDetailesService oAthUserDetailesService;
    @Autowired
    private OAuthAccessTokenService oAuthAccessTokenService;
    @Autowired
    private OAuthRefreshTokenService oAuthRefreshTokenService;
    /**
     *配置授权服务器的安全性，这实际上意味着/oauth/token端点。/oauth/authorize端点也需要是安全的，
     * 但这是一个普通的面向用户的端点，应该与UI的其他部分以相同的方式进行保护，因此这里不讨论。
     * 默认设置涵盖了最常见的需求，遵循OAuth2规范的建议，因此您不需要在这里做任何事情来启动和运行基本服务器
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启表单验证
        super.configure(security);
        /**
         * token 的验证方法
         * promiseAll()//允许所有
         * denyAll //拒绝所有
         * isAuthenticated() 通过验证
         * */
        security.checkTokenAccess("isAuthenticated()");
        /**
         * 默认Spring Security获取token的认证模式是基于http basic的，
         * client的client_id和client_secret是通过http的header或者url模式传递
         * 当启用这个配置之后，server可以从表单参数中获取相应的client_id和client_secret信息
         * */
        security.allowFormAuthenticationForClients();
        /**
         * 解码器配置
         * */
        security.passwordEncoder(getPassWordEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(oauthClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        DefaultTokenServicesCover defaultTokenServicesCover =getTokenService();
        endpoints.tokenServices(defaultTokenServicesCover);
        endpoints.userApprovalHandler(userApprovalHandler());
        endpoints.authenticationManager(getmanager(defaultTokenServicesCover));
        endpoints.reuseRefreshTokens(true);
        // 如果要使用RefreshToken可用，必须指定UserDetailsService
        endpoints.userDetailsService(oAthUserDetailesService);
    }


    @Bean
    public PasswordEncoder getPassWordEncoder() {
        // 使用系统的加密
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Primary
    public DefaultTokenServicesCover getTokenService(){
        DefaultTokenServicesCover tokenServices =new DefaultTokenServicesCover();
        tokenServices.setTokenStore(getTokenStor());
        tokenServices.setClientDetailsService(oauthClientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setReuseRefreshToken(true);
        tokenServices.setAuthenticationManager(getmanager(tokenServices));
        return tokenServices;
    }
    @Bean
    public OAuth2AuthenticationManager getmanager(@Autowired DefaultTokenServicesCover defaultTokenServicesCover){
        OAuth2AuthenticationManager oAuth2AuthenticationManager =new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setClientDetailsService(oauthClientDetailsService);
        oAuth2AuthenticationManager.setResourceId(ConstParam.RESOURCE_ID);
        oAuth2AuthenticationManager.setTokenServices(defaultTokenServicesCover);
        return oAuth2AuthenticationManager;
    }
    @Bean
    public TokenStoreCover getTokenStor(){
        TokenStoreCover tokenStoreCover =new TokenStoreCover();
        tokenStoreCover.setRefreshTokenService(oAuthRefreshTokenService);
        tokenStoreCover.setOAuthAccessTokenService(oAuthAccessTokenService);
        return tokenStoreCover;
    }
    @Bean
    public UserApprovalHandler userApprovalHandler() {
        // 存储用户的授权结果
        ApprovalStoreUserApprovalHandler handler = new ApprovalStoreUserApprovalHandler();
        handler.setApprovalStore(userApprovalService);
        handler.setRequestFactory(requestFactory());
        return handler;
    }
    @Bean
    public OAuth2RequestFactory requestFactory() {
        return new DefaultOAuth2RequestFactory(oauthClientDetailsService);
    }
}

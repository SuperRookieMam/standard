package com.standard.server.componet.config;

import com.standard.server.service.OAuthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class OAuthServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    OAuthClientDetailsService oAuthClientDetailsService;

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


    /**
     *  配置{@link ClientDetailsService}， 例如声明单个 客户机 及其属性。
     *  请注意,除非启用了{@link AuthenticationManager}，否则不启用密码授予(即使一些客户机允许)
     *  提供给{@link #configure(AuthorizationServerEndpointsConfigurer)}。
     *  至少有一个客户，或一个完整的 必须声明已形成的自定义{@link ClientDetailsService}，否则服务器将无法启动。
     * */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.oAuthClientDetailsService);
    }

    /**
     * 配置授权服务器端点的非安全性特性，如令牌存储、令牌 自定义、用户批准和授予类型。
     * 默认情况下，您不应该做任何事情，除非您需要 密码授予，在这种情况下，
     * 您需要提供一个{@link AuthenticationManager}。
     */
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {



//        AuthorizationServerTokenServices tokenService = tokenService();
//        endpoints.tokenServices(tokenService);
//        endpoints.userApprovalHandler(userApprovalHandler());
//        endpoints.reuseRefreshTokens(false);
//        // 如果要使用RefreshToken可用，必须指定UserDetailsService
//        endpoints.userDetailsService(userDetailesService);
//        endpoints.authenticationManager(((AuthorizationServerTokenService) tokenService).getAuthenticationManager());
    }





    @Bean
    public PasswordEncoder getPassWordEncoder() {
        // 使用系统的加密
        return new BCryptPasswordEncoder();
    }

}

package com.standard.resource.componet.config;


import com.standard.resource.componet.feature.DefaultTokenServicesCover;
import com.standard.resource.componet.feature.TokenStoreCover;
import com.standard.resource.service.OAuthClientDetailsService;
import com.standard.securityCommon.authentication.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/*@Configuration
@EnableConfigurationProperties(OAuth2SsoProperties.class)
@Import({ResourceServerTokenServicesConfiguration.class})*/
public class SsoConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DefaultTokenServicesCover defaultTokenServicesCover;
//    @Autowired
//    private FilterSecurityInterceptor filterSecurityInterceptor;
    @Autowired
    private OAuth2SsoProperties ssoProperties;
    @Autowired
    private UserInfoRestTemplateFactory restTemplateFactory;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //  认定所有的资源都是受保护的,都需要验证通过
         http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
         // 关闭网页sable认证
         http.csrf() .disable();
        //   设置登出路径，估计是 logout不需要权限
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
        //验证失败反回401 错误
        http.exceptionHandling() .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        //  单点登录时filterSecurityInterceptor的方法及投票过滤拦截
      // http.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
        //此security使用在outheclient 的配置上
        http.apply(new OAuth2ClientAuthenticationConfigurer(oauth2SsoFilter()));
    }


    /**
     * 其实这里可以自定义filter
     * 继承AbstractAuthenticationProcessingFilter
     * 来处理自己需要的逻辑
     * 这里配置的遇见login拦截
     */
   private OAuth2ClientAuthenticationProcessingFilter oauth2SsoFilter() {
        //拦截/login
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(ssoProperties.getLoginPath());
        //这里配置配置的远程获取user信息的，如果要自定义也可以
        filter.setRestTemplate(restTemplateFactory.getUserInfoRestTemplate());
        //这里配置远程定获取token的servicve貌似有默认实现
        filter.setTokenServices(defaultTokenServicesCover);
        //applicationContext context
        filter.setApplicationEventPublisher(getApplicationContext());
        //这里配置登录成功后的控制器
        filter.setAuthenticationSuccessHandler(loginSuccessHandler());
        return filter;
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }


    // 这配置覆盖
    private static class OAuth2ClientAuthenticationConfigurer
            extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private OAuth2ClientAuthenticationProcessingFilter filter;

        OAuth2ClientAuthenticationConfigurer(OAuth2ClientAuthenticationProcessingFilter filter) {
            this.filter = filter;
        }
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            OAuth2ClientAuthenticationProcessingFilter ssoFilter = this.filter;
            ssoFilter.setSessionAuthenticationStrategy(builder.getSharedObject(SessionAuthenticationStrategy.class));
            builder.addFilterAfter(ssoFilter,AbstractPreAuthenticatedProcessingFilter.class);
        }
    }
}

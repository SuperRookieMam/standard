package com.standard.resource.componet.config;

import com.standard.resource.componet.constpackage.ConstParam;
import com.standard.resource.componet.feature.DefaultTokenServicesCover;
import com.standard.resource.componet.feature.RequestAuthoritiesAccessDecisionVoterImpl;
import com.standard.resource.componet.feature.RequestAuthoritiesServiceImpl;
import com.standard.resource.componet.feature.TokenStoreCover;
import com.standard.resource.service.*;
import com.standard.securityCommon.access.RequestAuthoritiesAccessDecisionVoter;
import com.standard.securityCommon.access.RequestAuthoritiesFilterInvocationSecurityMetadataSource;
import com.standard.securityCommon.provider.RequestAuthoritiesService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Collections;
import java.util.List;

/*资源服务器*/
@EnableResourceServer
@EnableWebSecurity
public class ResourceConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAthUserDetailesService oAthUserDetailesService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private OAthGrantedAuthorityService oAthGrantedAuthorityService;
    @Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;
    @Autowired
    private OAuthAccessTokenService oAuthAccessTokenService;
    @Autowired
    private OAuthRefreshTokenService oAuthRefreshTokenService;
    /**
     * 配置对资源的保护模式
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 指定所有的资源都要被保护
      //  super.configure(http);
      http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
        // 增加自定义的资源授权过滤器
      http .addFilterBefore(interceptor(),FilterSecurityInterceptor.class);
        // 认定那些时资源？
      // http.requestMatcher(new BearerTokenRequestMatcher());

    }
    /**
     * 投票器初始化
     * */
    @Bean
    public FilterSecurityInterceptor interceptor() {
        // 添加过滤器
      FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        //投票过滤
      List<AccessDecisionVoter<?>> voters = Collections.singletonList(getmyVoter());
        // 只要有一个投票器通过了就可以访问资源
      // AccessDecisionManager accessDecisionManager = new AffirmativeBased(voters);
        // 所有的投票器通过了才能访问资源
        UnanimousBased unanimousBased =new UnanimousBased(voters);
        // 一般以上的投票器通过了才能访问资源
       // ConsensusBased consensusBased =new ConsensusBased(voters);
        //权限管理
        interceptor.setAccessDecisionManager(unanimousBased);
        //这里封装后面voter需要的参数，还有那些地方要用到我也不晓得
       interceptor.setSecurityMetadataSource(securityMetadataSource());
       //  todo  注意：这里默认的共享SecurityMetadataSource安全元素，默认的是true,
        // todo 也就是说第一次获取获取过后，后面的全部用第一次获取的来投票，
        // TODO 如果一个链条的投票安全元素不一样这里要把共享给关掉，不然会出一些幺蛾子
       interceptor.setObserveOncePerRequest(false);
       return interceptor;
    }
    public  RequestAuthoritiesAccessDecisionVoter getmyVoter(){
        RequestAuthoritiesAccessDecisionVoter voter =new RequestAuthoritiesAccessDecisionVoterImpl();
        ((RequestAuthoritiesAccessDecisionVoterImpl) voter).setOAthUserDetailesService(oAthUserDetailesService);
        ((RequestAuthoritiesAccessDecisionVoterImpl) voter).setUserRoleService(userRoleService);
        return voter;
    }
     //投票需要获取的元素，比较等等
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        RequestAuthoritiesFilterInvocationSecurityMetadataSource MetadataSource
                = new RequestAuthoritiesFilterInvocationSecurityMetadataSource();
        MetadataSource.setRequestAuthoritiesService( getRequestAuthoritiesService());
        return MetadataSource;
    }

    public RequestAuthoritiesService getRequestAuthoritiesService(){
        RequestAuthoritiesService requestAuthoritiesService = new RequestAuthoritiesServiceImpl();
        ((RequestAuthoritiesServiceImpl) requestAuthoritiesService).setOAthGrantedAuthorityService(oAthGrantedAuthorityService);
        return requestAuthoritiesService;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        // 指定这是一个restful service,不会保存会话状态
        resources.resourceId(ConstParam.RESOURCE_ID);
        resources.stateless(true);
        resources.tokenServices(getDefaultTokenServicesCover());
    }
    @Bean
    @Primary
    public DefaultTokenServicesCover getDefaultTokenServicesCover(){
        DefaultTokenServicesCover defaultTokenServicesCover =new DefaultTokenServicesCover();
        defaultTokenServicesCover.setTokenStore(getTokenStor());
        defaultTokenServicesCover.setClientDetailsService(oAuthClientDetailsService);
        defaultTokenServicesCover.setAuthenticationManager(getmanager(defaultTokenServicesCover));
        return defaultTokenServicesCover;
    }
    @Bean
    public OAuth2AuthenticationManager getmanager(@Autowired DefaultTokenServicesCover defaultTokenServicesCover){
        OAuth2AuthenticationManager oAuth2AuthenticationManager =new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setClientDetailsService(oAuthClientDetailsService);
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
//    //除了     LOGIONPATH 路径 其他的资源全部要 走资源服务器验证
//    static class BearerTokenRequestMatcher implements RequestMatcher {
//
//        private boolean matchHeader(HttpServletRequest request) {
//            String authHeader = request.getHeader("Authorization");
//            return StringUtils.startsWithIgnoreCase(authHeader, OAuth2AccessToken.BEARER_TYPE);
//        }
//        @Override
//        public boolean matches(HttpServletRequest request) {
//            return matchHeader(request) || matchParameter(request);
//        }
//        public boolean matchParameter(HttpServletRequest request){
//            return !StringUtils.isEmpty(request.getParameter(OAuth2AccessToken.ACCESS_TOKEN));
//        }
//    }
}

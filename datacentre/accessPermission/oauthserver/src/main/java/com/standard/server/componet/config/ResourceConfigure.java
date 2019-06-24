package com.standard.server.componet.config;

import com.standard.securityCommon.access.RequestAuthoritiesFilterInvocationSecurityMetadataSource;
import com.standard.securityCommon.provider.RequestAuthoritiesService;
import com.standard.server.componet.feature.DefaultTokenServicesCover;
import com.standard.server.service.OAuthAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/*资源服务器*/
@EnableResourceServer
@Order(99)
@EnableWebSecurity
public class ResourceConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    OAuthAccessTokenService oAuthAccessTokenService;
    @Autowired
    DefaultTokenServicesCover defaultTokenServicesCover;

    private  static  final String  LOGIONPATH ="/login";
    /**
     * 配置对资源的保护模式
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 指定所有的资源都要被保护
        super.configure(http);
        // 增加自定义的资源授权过滤器
        http.addFilterBefore(interceptor(), FilterSecurityInterceptor.class);
        //自定义拦截规则，要走资源服务器验证的请求，
        // 除了login，其他的请求都需要携带access_token这个参数
        http.requestMatcher(new BearerTokenRequestMatcher());
    }
    /**
     * 投票器初始化
     * */
    @Bean
    public FilterSecurityInterceptor interceptor() {
        // 添加过滤器
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        //投票过滤
        List<AccessDecisionVoter<?>> voters = Collections.singletonList(
                new RequestAuthoritiesAccessDecisionVoterImpl());
        //创建一个成功的控制权限
        //UnanimousBased unanimousBased =new UnanimousBased(voters);
        AccessDecisionManager accessDecisionManager = new AffirmativeBased(voters);

        //权限管理
        interceptor.setAccessDecisionManager(accessDecisionManager);
        //这里封装后面voter需要的参数，还有那些地方要用到我也不晓得
        interceptor.setSecurityMetadataSource(securityMetadataSource());
        return interceptor;
    }

    @Bean  //投票需要获取的元素，比较等等
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        RequestAuthoritiesFilterInvocationSecurityMetadataSource MetadataSource
                = new RequestAuthoritiesFilterInvocationSecurityMetadataSource();
       MetadataSource.setRequestAuthoritiesService(getRequestAuthoritiesService());
        return MetadataSource;
    }

    @Bean
    public RequestAuthoritiesService getRequestAuthoritiesService(){
        RequestAuthoritiesServiceImpl requestAuthoritiesService = new RequestAuthoritiesServiceImpl();
        return requestAuthoritiesService;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        // 指定这是一个restful service,不会保存会话状态
        resources.resourceId("1");
        resources.stateless(true);
        resources.tokenServices(defaultTokenServicesCover);

    }


    //除了     LOGIONPATH 路径 其他的资源全部要 走资源服务器验证
    static class BearerTokenRequestMatcher implements RequestMatcher {

        @Override
        public boolean matches(HttpServletRequest request) {
            return matchParameter(request);
        }
        public boolean matchParameter(HttpServletRequest request){
            if (LOGIONPATH.equals(request.getRequestURI())){
                return false;
            }else {
                return true;
            }
        }
    }
}

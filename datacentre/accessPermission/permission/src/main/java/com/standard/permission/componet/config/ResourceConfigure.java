package com.standard.permission.componet.config;

import com.standard.permission.componet.constpackage.ConstParam;
import com.standard.permission.componet.feature.DefaultTokenServicesCover;
import com.standard.permission.componet.feature.RequestAuthoritiesAccessDecisionVoterImpl;
import com.standard.permission.componet.feature.RequestAuthoritiesServiceImpl;
import com.standard.permission.service.OAthGrantedAuthorityService;
import com.standard.permission.service.OAthUserDetailesService;
import com.standard.permission.service.UserRoleService;
import com.standard.securityCommon.access.RequestAuthoritiesAccessDecisionVoter;
import com.standard.securityCommon.access.RequestAuthoritiesFilterInvocationSecurityMetadataSource;
import com.standard.securityCommon.provider.RequestAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Collections;
import java.util.List;

/*资源服务器*/
@EnableResourceServer
@Order(99)
@EnableWebSecurity
public class ResourceConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAthUserDetailesService oAthUserDetailesService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private DefaultTokenServicesCover defaultTokenServicesCover;
    @Autowired
    private OAthGrantedAuthorityService oAthGrantedAuthorityService;
    /**
     * 配置对资源的保护模式
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 指定所有的资源都要被保护
      //  super.configure(http);
      http.authorizeRequests()
              .antMatchers("/static/**","/Test/**")//静态资源可请建一个sttatic的文件加，我这下面的请求将被允许
              .permitAll()
          .and()
              .antMatcher("/**")
              .authorizeRequests()
              .anyRequest()
              .authenticated()
              .and()
              .formLogin()
              .loginProcessingUrl("/login")
              .permitAll()
          .and()
              .addFilterBefore(interceptor(),FilterSecurityInterceptor.class);

    }
    /**
     * 投票器初始化
     * */
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
        resources.tokenServices(defaultTokenServicesCover);
    }
}

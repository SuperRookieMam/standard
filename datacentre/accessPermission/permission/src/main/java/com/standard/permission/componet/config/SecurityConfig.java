package com.standard.permission.componet.config;

import com.standard.permission.service.OAthUserDetailesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 开启Security 保护 一下的几个路径为受保护的
 */
@EnableWebSecurity
//全局开启方法权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAthUserDetailesService oAthUserDetailesService;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
             .and()
                .authorizeRequests()
                .antMatchers("/static/**")//静态资源可请建一个sttatic的文件加，我这下面的请求将被允许
                .permitAll()
             .and()
                .formLogin()
                .permitAll()
             .and()
                .csrf()//关闭网页跨域验证
                .disable();
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(oAthUserDetailesService);
    }


}

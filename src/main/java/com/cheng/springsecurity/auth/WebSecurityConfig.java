package com.cheng.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private MyLogoutHandler logoutHandler;
    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 通过auth可以在内存中构建虚拟的用户名和密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 任何请求都会被拦截，包括登陆 html
        http.authorizeRequests()
                // 允许登录界面通过
                .antMatchers("/login").permitAll()
                .antMatchers("/users","/roles")
                .hasAnyAuthority("ROLE_user","ROLE_admin")
                .antMatchers("/menus","/others")
                .hasAnyRole("admin")
                .anyRequest()
                .authenticated()
                .and()
                // 允许退出登录通过
                .logout().permitAll()
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                // 设置登录页面
                .formLogin()
                // 设置form表单的登录控制器，默认是login 与表单的action对应
                .loginProcessingUrl("/login")
                // 登录名和密码
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功或失败的处理
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                // 用户未登录的处理
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
        // 关闭跨域攻击
        http.csrf().disable();
    }
}

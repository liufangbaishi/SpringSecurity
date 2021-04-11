package com.cheng.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    @Autowired
    private MyInvalidSessionStrategy sessionStrategy;
    @Autowired
    private MyExpiredSessionStrategy expiredSessionStrategy;
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
//                .antMatchers("/users","/roles")
                // ROLE_user和ROLE_admin这两个角色，可以访问/users和/roles这两个控制器
//                .hasAnyAuthority("ROLE_user","ROLE_admin")
//                .antMatchers("/menus","/others")
//                .hasAnyRole("admin")
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
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement().invalidSessionStrategy(sessionStrategy)
                // 最大允许登录数量
                .maximumSessions(1)
                // 是否允许另一个账户登录  true:无法登录，返回失败，false:允许登录，将对方挤下线
                .maxSessionsPreventsLogin(false)
                // 被挤下线处理方式
                .expiredSessionStrategy(expiredSessionStrategy);
                 // 无状态的,任何时候都不会使用session
                // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 关闭跨域攻击
        http.csrf().disable();
    }
}

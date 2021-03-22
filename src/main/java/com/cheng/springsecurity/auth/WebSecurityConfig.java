package com.cheng.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

    /**
     * 通过auth可以在内存中构建虚拟的用户名和密码
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 创建了两个用户 user 和 admin 密码均为123 角色分别是 user 和 admin
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("123"))
                .roles("user")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("123"))
                .roles("admin")
                // 配置auth的加密方式为passwordEncoder
                .and()
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
                .antMatchers("/login","/login.html").permitAll()
                // 设置静态权限
                // 用户和角色的请求 需要ROLE_user和ROLE_admin的权限
                .antMatchers("/users","/roles")
                //.hasAuthority("ROLE_user")
                .hasAnyAuthority("ROLE_user","ROLE_admin")
                // 菜单和其他的请求，需要admin的角色
                .antMatchers("/menus","/others")
                //.hasRole("admin")
                .hasAnyRole("admin")
                .anyRequest()
                .authenticated()
                .and()
                // 设置登录页面
                .formLogin().loginPage("/login.html")
                // 设置form表单的登录控制器，默认是login 与表单的action对应
                .loginProcessingUrl("/login")
                // 登录名和密码
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功后跳转路径
                .defaultSuccessUrl("/home");
        // 关闭跨域攻击
        http.csrf().disable();
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 开启httpBasic认证
//        http.httpBasic()
//                .and()
//                .authorizeRequests() // 认证所有的请求
//                .anyRequest()
//                .authenticated(); // 任何请求都必须认证成功
//    }
}

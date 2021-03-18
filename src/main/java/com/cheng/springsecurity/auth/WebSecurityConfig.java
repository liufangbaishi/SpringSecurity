package com.cheng.springsecurity.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启httpBasic认证
//        http.httpBasic()
//                .and()
//                .authorizeRequests() // 认证所有的请求
//                .anyRequest()
//                .authenticated(); // 任何请求都必须认证成功
        // 任何请求都会被拦截，包括登陆 html
        http.authorizeRequests()
                // 允许登录界面通过
                .antMatchers("/login","/login.html").permitAll()
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
}

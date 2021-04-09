package com.cheng.springsecurity.auth;

import com.cheng.springsecurity.response.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出成功
 */
@Component
public class MyLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        Result result = Result.ok().message("注销成功");
        super.WriteJSON(httpServletRequest,httpServletResponse,result);
    }
}
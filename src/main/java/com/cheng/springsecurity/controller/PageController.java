package com.cheng.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    /**
     * 主页面
     * @return
     */
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    /**
     * 用户管理
     * @return
     */
    @GetMapping("/users")
    public String users(){
        return "users";
    }

    /**
     * 角色管理
     * @return
     */
    @GetMapping("/roles")
    public String roles(){
        return "roles";
    }

    /**
     * 菜单管理
     * @return
     */
    @GetMapping("/menus")
    public String menus(){
        return "menus";
    }

    /**
     * 其他管理
     * @return
     */
    @GetMapping("/others")
    public String others(){
        return "others";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}


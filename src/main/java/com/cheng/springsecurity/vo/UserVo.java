package com.cheng.springsecurity.vo;

import lombok.Data;

@Data
public class UserVo {
    private String username;

    private String nickname;

    private String email;

    private Integer sex;
}

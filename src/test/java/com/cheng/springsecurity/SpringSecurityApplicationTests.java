package com.cheng.springsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void test() {
        // 加密
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
        // 匹配是否相同，不能解密
        boolean matches = passwordEncoder.matches("123", "$2a$10$DYyuC5bgnUh50NjphOVwoOSAshgqY9wVQfn9jyrFbOQWFU1F2BxPW");
        System.out.println(matches);

        // 盐值 每次生成的盐都是不一样的 => 就算在数据库中破解了一个密码，还是无法猜到其他的
        System.out.println(BCrypt.gensalt()); // 获得盐值
    }

}

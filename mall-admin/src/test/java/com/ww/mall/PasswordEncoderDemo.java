package com.ww.mall;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderDemo {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void t() {
        String pass = "123456";
        String passHash = passwordEncoder.encode(pass);
        System.out.println("passHash=" + passHash);
        final boolean matches = passwordEncoder.matches(pass, passHash);
        System.out.println(matches);
    }
}

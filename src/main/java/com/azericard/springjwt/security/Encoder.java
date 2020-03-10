package com.azericard.springjwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Configuration
public class Encoder {
    private final static String ALGORITHM = "bcrypt";

    @Bean
    public static PasswordEncoder build() {
        return new DelegatingPasswordEncoder(ALGORITHM,
                new HashMap<String, PasswordEncoder>(11) {{
                    put("pbkdf2", new org.springframework.security.crypto.password.Pbkdf2PasswordEncoder());
                    put("bcrypt", new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder());
                    put("scrypt", new org.springframework.security.crypto.scrypt.SCryptPasswordEncoder());
                    put("argon2", new org.springframework.security.crypto.argon2.Argon2PasswordEncoder());
                }}
        );
    }
}

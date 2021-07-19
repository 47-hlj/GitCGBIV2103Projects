package com.cy.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author 47HLJ
 * @date 2021/7/16 16:03
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }
}


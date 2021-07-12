package com.cy.jt.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author 47HLJ
 * @date 2021/7/12 12:48
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class JedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(JedisApplication.class,args);
    }
}

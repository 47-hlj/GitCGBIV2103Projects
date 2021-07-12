package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author 47HLJ
 * @date 2021/7/12 13:09
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);
    }
}

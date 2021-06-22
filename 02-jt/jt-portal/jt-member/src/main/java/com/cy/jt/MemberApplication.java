package com.cy.jt;

import org.springframework.boot.SpringApplication;
import com.cy.jt.common.basic.util.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 这是springboot启动类
 */
@SpringBootApplication
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
        System.out.println(StringUtils.isEmpty("hello"));
    }
}

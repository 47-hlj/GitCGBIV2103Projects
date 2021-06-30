package com.cy.jt.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootTest
public class DataSourceTest {
//    @Slf4j 当类上加上了此注解默认添加了如下语句
//    private static final Logger log= LoggerFactory.getLogger(DataSourceTest.class);

    @Autowired
    private DataSource dataSource;//此对象在springboot启动时已自动注入

    @Test
    void testGetConnection() throws Exception{
        Connection conn =dataSource.getConnection();
//        System.out.println("conn="+conn);
        log.debug("conn is {}",conn);
    }




}

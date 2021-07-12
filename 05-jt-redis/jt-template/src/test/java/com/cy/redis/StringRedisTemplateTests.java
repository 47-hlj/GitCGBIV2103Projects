package com.cy.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author 47HLJ
 * @date 2021/7/12 13:11
 */
@SpringBootTest
public class StringRedisTemplateTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testConnection(){
        String pong=stringRedisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(pong);
    }

    @Test
    void testStringOper() throws InterruptedException {
        //获取操作key,value的对象
        ValueOperations<String, String> vo = stringRedisTemplate.opsForValue();
        vo.set("key1","100");
        vo.set("key2","200");
        vo.increment("key2");
        //存储key3,并设置key的有效时长
        vo.set("key3","300",1, TimeUnit.SECONDS);
        String v1=vo.get("key1");
        String v2=vo.get("key2");
        System.out.println("v1="+v1+";v2="+v2);
        TimeUnit.SECONDS.sleep(2);
        String v3=vo.get("key3");
        System.out.println("v3="+v3);
    }
}

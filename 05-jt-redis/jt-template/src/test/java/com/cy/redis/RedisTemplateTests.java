package com.cy.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 47HLJ
 * @date 2021/7/11 22:05
 */
@SpringBootTest
public class RedisTemplateTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSetData(){
        SetOperations setOperations=redisTemplate.opsForSet();
        setOperations.add("setKey1","A","B","C","C");
        Object members=setOperations.members("setKey1");
        System.out.println("setKeys="+members);
    }

    @Test
    void testListData(){
        //向list集合放数据
        ListOperations listOperations=redisTemplate.opsForList();
        listOperations.leftPush("lstKey1","100");
        listOperations.leftPushAll("lstKey1","200","300");
        listOperations.leftPush("lstKey1", "100", "105");
        listOperations.rightPush("lstKey1", "700");
        Object value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
        //从list集合取数据
        Object v1=listOperations.leftPop("lstKey1");
        System.out.println("left.pop.0="+v1);
        value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
    }

    /**通过此方法操作redis中的hash数据*/
    @Test
    void testHashData(){
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String,String> blog=new HashMap<>();
        blog.put("id", "1");
        blog.put("title", "hello redis");
        hashOperations.putAll("blog", blog);
        hashOperations.put("blog", "content", "redis is very good");
        Object hv=hashOperations.get("blog","id");
        System.out.println(hv);
        Object entries=hashOperations.entries("blog");
        System.out.println("entries="+entries);
    }
}

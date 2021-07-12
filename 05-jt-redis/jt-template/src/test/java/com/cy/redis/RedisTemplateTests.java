package com.cy.redis;

import com.cy.redis.pojo.Blog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 47HLJ
 * @date 2021/7/12 13:49
 */
@SpringBootTest
public class RedisTemplateTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void testConnection(){
        String result= redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(result);
    }

    //定义清除数据库数据的方法
    @Test
    void testFlushdb(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //redisConnection.flushDb();
                redisConnection.flushAll();
                return "flush ok";
            }
        });
    }

    @Test
    void testStringOper01(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //RedisTemplate存储数据时会默认基于JDK的序列化机制,将数据序列化以后再进行存储
        valueOperations.set("address","beijing");
        //对数据进行反序列化实现
        Object value=valueOperations.get("address");
        System.out.println(value);
    }

    @Test
    void testStringOper02() throws JsonProcessingException {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Blog blog=new Blog(10,"study redis");
        //blog.setId(10);
        //blog.setTitle("study redis");
        valueOperations.set("blog",blog);//序列化
        //默认反序列化(使用指定的RedisTemplate)
        //LinkedHashMap map=(LinkedHashMap)valueOperations.get("blog");//反序列化
        //String json=new ObjectMapper().writeValueAsString(map);
        //blog=new ObjectMapper().readValue(json,Blog.class);
        //假如在RedisTemplate中配置了 objectMapper.enableDefaultTyping可以直接转Blog对象
        blog=(Blog)valueOperations.get("blog");//反序列化
        System.out.println("blog="+blog);

    }

    //对hash类型数据进行测试
    @Test
    void testHashOper01(){
        //获取操作hash数据的对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //向redis存一个对象
        Map<String,String> blog=new HashMap<>();
        blog.put("id", "1");
        blog.put("title", "hello redis");
        hashOperations.putAll("blog", blog);
        hashOperations.put("blog","content","redis hash type");
        //从redis中获取存储的hash数据
        Object contentValue=hashOperations.get("blog","content");
        System.out.println(contentValue);
        //获取blog这个key对应的所有属性以及属性的值
        blog=hashOperations.entries("blog");
        System.out.println(blog);
    }

    @Test
    void testListOper(){
        ListOperations listOperations = redisTemplate.opsForList();
        //实现一个先进先出队列
        listOperations.leftPush("lst1","100");
        listOperations.leftPush("lst1","200");
        listOperations.leftPush("lst1","300");
        Object v1=listOperations.rightPop("lst1");
        Object v2=listOperations.rightPop("lst1");
        Object v3=listOperations.rightPop("lst1");
        System.out.println(v1+"/"+v2+"/"+v3);
        //从right位置开始放
        listOperations.rightPush("lst2","A");
        listOperations.rightPush("lst2","B");
        listOperations.rightPush("lst2","C");
        listOperations.rightPush("lst2","D","E");
        //获取lst2集合中指定位置的数据
        System.out.println(listOperations.range("lst2",0,-1));
    }

    @Test
    void testSetOper(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("set01","A","B","C","A");
        Set set01 = setOperations.members("set01");
        System.out.println(set01);
    }
}

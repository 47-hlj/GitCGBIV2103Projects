package com.cy.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author 47HLJ
 * @date 2021/7/13 9:50
 */
@SpringBootTest
public class MasterSlaveTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void testMasterReadWrite(){//6379
        //1.获取数据操作对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //2.读写数据
        valueOperations.set("city","beijing");
        Object city=valueOperations.get("city");
        System.out.println(city);
    }
    @Test
    void testSlaveRead(){//6380
        //1.获取数据操作对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //2.读数据
        Object city=valueOperations.get("city");
        System.out.println(city);
    }
}

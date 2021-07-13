package com.cy.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author 47HLJ
 * @date 2021/7/12 16:40
 */
@SpringBootTest
public class JedisTransactionTests {

    @Test
    public void testTx(){
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("w", "100");
            multi.set("z", "200");
            //int num=100/0; 出现异常会进入catch,然后回滚事务
            //提交事务
            multi.exec();
        }catch (Exception e){
            e.printStackTrace();
            //事务回滚
            multi.discard();
        }finally {
            jedis.close();
        }
    }
}

package com.cy.jt.redis;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 47HLJ
 * @date 2021/7/11 20:14
 */
@SpringBootTest
public class JedisTests {
    /*基于jedis操作redis中的字符串
     *场景应用:保存登录时的验证码,手机短信验证码,购物车数量递增,博客文字数量,...
     */
    @Test
    void testStringOper() throws InterruptedException {
        //构建redis客户端对象 jedis,我们在连接redis时,需要在redis的服务端
        //的redis.conf配置文件中,将bind 127.0.0.1这个配置注释掉,
        //修改保护模式protected-mode为no,改完以后要重启
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //jedis.auth("123456") 假如设置了密码还需要这个动作
        //测试是否连接到了redis
        String result=jedis.ping();
        System.out.println(result);
        //向数据库存储数据
        jedis.set("id","10001");
        jedis.set("name","jack");
        jedis.incr("id");
        jedis.incrBy("id",2);
        jedis.set("token",UUID.randomUUID().toString());
        //设置key有效时长
        jedis.expire("token",60*30);
        //从数据库取数据
        String id=jedis.get("id");
        String name=jedis.get("name");
        TimeUnit.SECONDS.sleep(1);//休眠1秒
        String token=jedis.get("token");
        System.out.println("id="+id+";name="+name+";token="+token);
        jedis.close();
    }

    @Test
    void testJsonOper(){
        //构建对象
        Map<String,Object> map=new HashMap<>();
        map.put("id",100);
        map.put("title","spring 认证");
        map.put("content","very good");
        //将对象转换为json格式字符串
        Gson gson=new Gson();
        String jsonStr=gson.toJson(map);
        //将json字符串写入到redis
        Jedis jedis=new Jedis("192.168.126.128",6379);
        jedis.set("user",jsonStr);
        //读取redis中数据
        jsonStr=jedis.get("user");
        System.out.println(jsonStr);
        Map<String,Object> obj=gson.fromJson(jsonStr,Map.class);
        System.out.println(obj);
        jedis.close();
    }
    @Test
    void testRedisHash01(){
        //1.建立连接
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //2.存储一篇博客信息
        jedis.hset("article","id","1");
        jedis.hset("article","title","mybatis");
        jedis.hset("article","content","framework");
        //3.获取博客内容并输出
        String id=jedis.hget("article","id");
        String title=jedis.hget("article","title");
        String content= jedis.hget("article","content");
        System.out.println(id+"/"+title+"/"+content);
        //4.释放资源
        jedis.close();
    }
    @Test
    void testRedisHash02(){
        //1.建立连接
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //2.存储一篇博客信息
        Map<String,String> map=new HashMap<>();
        map.put("x","100");
        map.put("y","200");
        jedis.hset("point",map);
        //3.获取博客内容并输出
        map=jedis.hgetAll("point");
        System.out.println(map);
        //4.释放资源
        jedis.close();
    }

    /**实现一个秒杀队列*/
    @Test
    void testList01(){
        //1.连接redis
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //2.向队列存数据
        jedis.rpush("list1","A","B","C");
        //3.按先进先出的顺序从队列取数据
        Long n=jedis.llen("list1");//获取队列长度
        List<String> list=jedis.lpop("list1",n.intValue());
        System.out.println(list);
        //4.释放资源
        jedis.close();
    }


    /**实现一个阻塞式队列*/
    @Test
    void testList02(){
        //1.连接redis
        Jedis jedis=new Jedis("192.168.126.128",6379);
        //2.向队列存数据
        //jedis.lpush("list1","A","B","C");
        //3.按先进先出的顺序从队列取数据
        List<String> list= jedis.brpop(40,"list1");
        System.out.println(list);
        //4.释放资源
        jedis.close();
    }
    @Test
    void testSet01() {
        //1.连接redis
        Jedis jedis = new Jedis("192.168.126.128", 6379);
        //2.朋友圈点赞
        jedis.sadd("count", "1", "1", "2");
        //3.取出点赞数
        Set<String> set = jedis.smembers("count");
        System.out.println(set);
        //4.释放资源
        jedis.close();
    }

}

package com.cy.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 47HLJ
 * @date 2021/7/12 13:47
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        //1.构建RedisTemplate对象
        RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();
        //2.设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //3.定义序列化方式(在这里选择了jackson)
        Jackson2JsonRedisSerializer redisSerializer= new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        //设置要序列化的域(通过所以get方法获取所有属性名以及值)
        //所有访问修饰符修饰的方法,any表示任意级别访问修饰符修饰的属性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //启动输入域检查(类不能是final修饰的),并基于json串中的key将其值存储到pojo对象
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        redisSerializer.setObjectMapper(objectMapper);
        //4.设置RedisTemplate的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(redisSerializer);
        //spring规范中假如修改bean对象的默认特性,建议调用一下afterPropertiesSet()
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

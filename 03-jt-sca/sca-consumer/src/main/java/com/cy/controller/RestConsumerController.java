package com.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/consumer/")
public class RestConsumerController {
    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Long ...id){
        System.out.println("id1="+id);
        System.out.println("id2="+ Arrays.toString(id));
        String idArrays=Arrays.toString(id);
        idArrays=idArrays.substring(1,idArrays.length()-1);
        System.out.println("idArray="+idArrays);
        String url=String.format("http://%s/provider/%s","sca-provider",idArrays);
//        loadBalancedRestTemplate.delete(url,id);
//        return "delete ok";
        //假如需要获取服务提供方，执行的删除操作结果，可以通过如下方式进行实现
        ResponseEntity<String> exchange = loadBalancedRestTemplate.exchange(
                url, //请求url
                HttpMethod.DELETE, //请求方式
                null, //请求实体参数
                String.class);//响应的数据类型
        return exchange.getBody();//响应体
    }

    /**
     * 添加操作
     * @param request
     * @return
     */
    @PostMapping
    public Map<String ,Object> doCreate(@RequestBody Map<String, Object> request){
        //...后续要将请求数据写入到数据
        System.out.println("consumer.request="+request);
        String url=String.format("http://%s/provider/","sca-provider");
        return loadBalancedRestTemplate.postForObject(url,request,Map.class);
//        return "create ok";
    }

    /**
     * 修改操作
     * @param request
     * @return
     */
    @PutMapping
    public Map<String,Object> doUpdate(@RequestBody Map<String ,Object> request){
        System.out.println("consumer.request="+request);
        String url=String.format("http://%s/provider/","sca-provider");
        //如下方式的更新，默认没有返回值
//        loadBalancedRestTemplate.put(url,request);

        //假如希望获取服务器提供方式执行更新操作时的响应结果，可以用方式如下
        ResponseEntity<Map> responseEntity=loadBalancedRestTemplate.exchange(
                url,//请求url
                HttpMethod.PUT,//请求方式
                new HttpEntity<>(request),//请求实体
                Map.class//响应数据类型
        );
        return responseEntity.getBody();//获取响应体数据
//        return "update ok";
    }

}

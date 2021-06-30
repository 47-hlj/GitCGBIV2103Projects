package com.cy.controller;

import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/provider/")
public class RestProviderController {
    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Long ...id){
        return Arrays.toString(id)+" id deleted";
    }

    /**
     * 添加操作
     * @param map
     * @return
     */
    @PostMapping
    public Map<String, Object> doCreate(@RequestBody Map<String, Object> map){
        //...后续要将请求数据写入到数据
        System.out.println("provider.map="+map);
        // 定义响应结果
        Map<String ,Object> responseMap=new HashMap<>();
        responseMap.put("status",200);
        responseMap.put("message","insert ok");
        return responseMap;
    }

    @PutMapping
    public Map<String,Object> doUpdate(@RequestBody Map<String,Object> map){
        //...后续要将请求数据写入到数据
        System.out.println("provider.map="+map);
        // 定义响应结果
        Map<String ,Object> responseMap=new HashMap<>();
        responseMap.put("status",200);
        responseMap.put("message","insert ok");
        return responseMap;
    }

}

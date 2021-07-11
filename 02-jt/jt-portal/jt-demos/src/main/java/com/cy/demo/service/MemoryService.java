package com.cy.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MemoryService {

    private Map<String,Object> cache=new ConcurrentHashMap<String, Object>();
    public List<Map<String,Object>> list(){
        if(cache.containsKey("memoryKey"))
            return (List<Map<String, Object>>)cache.get("memoryKey");
        System.out.println("get data from database");
        Map<String,Object> m1=new HashMap<String, Object>();
        Map<String,Object> m2=new HashMap<String, Object>();
        m1.put("id",100);
        m1.put("title","title-A");
        m2.put("id",101);
        m2.put("title","title-B");
        List<Map<String , Object>> list = new ArrayList<Map<String, Object>>();
        list.add(m1);
        list.add(m2);
        cache.put("memoryKey",list);
        return list;
    }
}

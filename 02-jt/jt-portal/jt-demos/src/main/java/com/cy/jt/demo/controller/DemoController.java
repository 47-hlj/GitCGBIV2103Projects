package com.cy.jt.demo.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 通过此对象演示CRUD请求的处理
 * restful规范=动作+url
 * @GetMapping
 * @PostMapping
 * @PutMapping
 * @DeleteMapping
 * @PatchMapping
 */

//@Lazy  延迟加载
@RestController
@RequestMapping("/demo")
public class DemoController {

    DemoController(){
        System.out.println("DemoController()");
    }

    //J.U.C 包下的线程安全的对象(提供了对长整型数据的原子操作，底层CAS算法)
    private AtomicLong counter=new AtomicLong(1);
    @GetMapping("{id}")
    public String doRetrieveById(@PathVariable Integer id) throws Exception{
        String tName = Thread.currentThread().getName();
        System.out.println(counter.getAndIncrement());
//        Thread.sleep(3000);
        TimeUnit.SECONDS.sleep(3);
        return tName+ "find result by "+id;
    }

    @PutMapping
    public String doUpdate(@RequestBody  Map<String,Object> map){
        return map.toString()+" is updated";
    }

    @PostMapping
    public String doCreate(@RequestBody  Map<String,Object> map){
        return map.toString()+" is created";
    }

//    @RequestMapping
    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Integer ...id){
        return Arrays.toString(id)+" id deleted";
    }
}

package com.cy.jt.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 通过此对象演示CRUD请求的处理
 * restful规范=动作+url
 * @GetMapping
 * @PostMapping
 * @PutMapping
 * @DeleteMapping
 * @PatchMapping
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PutMapping
    public String doUpdate(@RequestBody  Map<String,Object> map){
        return map.toString()+" is updated";
    }

    @PostMapping
    public String doCreate(@RequestBody  Map<String,Object> map){
        return map.toString()+" is created";
    }

    @GetMapping("{id}")
    public String doRetrieveById(@PathVariable Integer ...id){
        return "find result by "+id;
    }


//    @RequestMapping
    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Integer ...id){
        return Arrays.toString(id)+" id deleted";
    }
}

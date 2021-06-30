package com.cy.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 4.EnableFeignClients+FeignClient方式
 */
//@FeignClient 描述接口时，用于告诉spring要为此接口创建实现类
@FeignClient(value = "sca-provider")//value的值为你要调用的服务名
public interface RemoteProviderService {

    @GetMapping("/provider/echo/{msg}")
    String doEcho(@PathVariable("msg") String msg);
}

package com.cy.service.feign;

import com.cy.common.domain.SysNotice;
import com.cy.common.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * value 表示远程服务名
 * contextId 为@FeignClient注解描述的接口类全名，首字母小写
 * url 要与调用的远程服务的url相同
 */
@FeignClient(value = "jt-system",contextId = "remoteNoticeService")
public interface RemoteNoticeService {

    @GetMapping("/notice/")
    public JsonResult doSelectNotice(SysNotice notice);
}

package com.cy.controller;

import com.cy.common.domain.SysNotice;
import com.cy.common.vo.JsonResult;
import com.cy.service.feign.RemoteNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortalsController {

    @Autowired
    private RemoteNoticeService remoteNoticeService;
    @GetMapping("/portals/notice/")
    public JsonResult doSelectNotice(SysNotice notice){
        //如何调用notice服务？
        return remoteNoticeService.doSelectNotice(notice);
    }
}

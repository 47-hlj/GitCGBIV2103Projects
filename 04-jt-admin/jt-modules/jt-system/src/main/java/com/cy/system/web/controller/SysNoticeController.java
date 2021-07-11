package com.cy.system.web.controller;

import com.cy.common.domain.SysNotice;
import com.cy.common.vo.JsonResult;
import com.cy.system.web.util.PageUtils;
import com.cy.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/notice/")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    @GetMapping
    public JsonResult doFindNotices(SysNotice sysNotice){
//        return new JsonResult(sysNoticeService.selectNotices(sysNotice));

//        return new JsonResult(
//                PageHelper.startPage(1,2)//启动分页拦截器
//                .doSelectPageInfo(()->//执行查询业务
//                sysNoticeService.selectNotices((sysNotice))
//       ));

//        return new JsonResult(PageHelper.startPage(1,2).doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                sysNoticeService.selectNotices(sysNotice);
//            }
//        }));
        //上面分页的简化写法
        return new JsonResult(PageUtils.startPage().doSelectPageInfo(()->{
            sysNoticeService.selectNotices(sysNotice);
        }));
    }


    @GetMapping("{id}")
    public JsonResult doSelectById(@PathVariable Long id){
        return new JsonResult(sysNoticeService.seleceById(id));
    }

    @DeleteMapping("{id}")
    public JsonResult doDeleteById(@PathVariable Long ...id){
        sysNoticeService.deleteById(id);
        return new JsonResult("delete ok");
    }

    @PostMapping
    public JsonResult doInsertNotice(SysNotice notice){
        sysNoticeService.insertNotice(notice);
        return new JsonResult("insert ok");
    }

    @PutMapping
    public JsonResult doUpdateNotice(SysNotice notice){
        sysNoticeService.updateNotice(notice);
        return new JsonResult("update ok");
    }

}

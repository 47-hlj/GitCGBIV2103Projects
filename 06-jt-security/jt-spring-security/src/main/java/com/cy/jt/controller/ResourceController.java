package com.cy.jt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 47HLJ
 * @date 2021/7/19 8:36
 */
@RestController
public class ResourceController {

    /**
     * 访问doCreate方法必须拥有/doCreate权限
     * @return
     */
    @PreAuthorize("hasAuthority('sys:res:create')")
    @RequestMapping("/doCreate")
    public String doCreate(){
        return "add resource";
    }

    /**
     * 访问doUpdate方法时必须拥有jinpai角色
     * @return
     */
    @PreAuthorize("hasRole('jinpai')")
    @RequestMapping("doUpdate")
    public String doUpdate(){
        //......
        return "update resource";
    }

    @PreAuthorize("hasAuthority('sys:res:delete')")
    @RequestMapping("/doDelete")
    public String doDelete(){
        return "delete resource";
    }

    @PreAuthorize("hasAuthority('sys:res:retrieve')")
    @RequestMapping("/doRetrieve")
    public String doRetrieve(){
        return "retrieve resource";
    }

}

package com.cy.jt.system.web.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static <T>Page<T> startPage(){
        //从spring中拿到request对象
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //基于请求对象获取请求中的参数
        int pageCurrent= Integer.parseInt(request.getParameter("pageCurrent"));
        int pageSize=Integer.parseInt(request.getParameter("pageSize"));
        return PageHelper.startPage(pageCurrent,pageSize);
    }
}

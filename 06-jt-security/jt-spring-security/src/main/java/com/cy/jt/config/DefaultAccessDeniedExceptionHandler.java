package com.cy.jt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 47HLJ
 * @date 2021/7/19 8:38
 */
public class DefaultAccessDeniedExceptionHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        //设置响应数据的编码
        response.setCharacterEncoding("utf-8");
        //告诉浏览器要响应的内容类型,以及编码
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        map.put("state",403);
        map.put("message","没有此资源的访问权限");
        PrintWriter out=response.getWriter();
        out.println(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}

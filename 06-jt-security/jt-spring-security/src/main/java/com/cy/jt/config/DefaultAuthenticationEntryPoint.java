package com.cy.jt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 47HLJ
 * @date 2021/7/19 8:40
 * 访问需要认证的资源时,假如还没有认证则会抛出异常,然后通过此对象处理异常
 */
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
        //设置响应数据的编码
        response.setCharacterEncoding("utf-8");
        //告诉浏览器要响应的内容类型,以及编码
        response.setContentType("application/json;charset=utf-8");
        Map<String,Object> map=new HashMap<>();
        map.put("state",401);
        map.put("message","请先登录");
        PrintWriter out=response.getWriter();
        out.println(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }
}

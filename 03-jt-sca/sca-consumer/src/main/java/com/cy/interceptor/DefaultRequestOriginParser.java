package com.cy.interceptor;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 47HLJ
 * @date 2021/7/5 9:04
 * 定义请求参数解析器
 */
@Component
public class DefaultRequestOriginParser implements RequestOriginParser {
    //http://ip:port/path?origin=app1
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin=request.getParameter("origin");
        return origin;
    }
}

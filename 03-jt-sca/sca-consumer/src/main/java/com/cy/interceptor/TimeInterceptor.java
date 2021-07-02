package com.cy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author 47HLJ
 * @date 2021/7/2 11:35
 */
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        LocalDateTime time=LocalDateTime.now();
        int hour=time.getHour();
        System.out.println("hour="+hour);
        if(hour<12||hour>23){
            throw new RuntimeException("请在指定时间访问");
        }
        return true;
    }

}

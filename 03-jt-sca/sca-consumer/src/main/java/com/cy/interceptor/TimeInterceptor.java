package com.cy.interceptor;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author 47HLJ
 * @date 2021/7/2 11:35
 */
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 此方法是在@Controller对象方法执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception{
        LocalDateTime time=LocalDateTime.now();
        int hour=time.getHour();
        System.out.println("hour="+hour);
        if(false){
            throw new RuntimeException("请在指定时间访问");
        }
        //true表示放行
        return true;
    }
}

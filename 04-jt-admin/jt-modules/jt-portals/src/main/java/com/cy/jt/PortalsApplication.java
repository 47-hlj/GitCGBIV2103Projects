package com.cy.jt;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@EnableFeignClients
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PortalsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalsApplication.class,args);
    }

    /**
     * Feign方式调用时的请求拦截器
     * 通过此拦截器拦截到请求数据以后
     * 可以结合业务对请求数据进行处理
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            /**
             * 在此方法中可以通过requestTemplate对象向新的请求中写数据
             * @param requestTemplate
             */
            @Override
            public void apply(RequestTemplate requestTemplate) {
                //1.获取原有请求中的数据
                HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                //2.将原有请求的数据添加到新的请求中
                //requestTemplate.header();//向请求体中添加数据
                requestTemplate.query("pageCurrent",request.getParameter("pageCurrent"));
                requestTemplate.query("pageSize",request.getParameter("pageSize"));
            }
        };
    }
}

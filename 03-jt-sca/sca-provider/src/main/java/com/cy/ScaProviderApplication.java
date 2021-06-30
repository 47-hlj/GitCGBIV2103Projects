package com.cy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ScaProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaProviderApplication.class,args);
    }

    @Value("${server.port}")
    private String server;


    @RefreshScope //动态刷新配置
    @RestController
    public class ProviderController{

        @Value("${logging.level.com.cy: error}")
        private String logLevel;

        @Value("${server.tomcat.threads.max: 200}")
        private String maxThread;

        @Value("${page.pageSize: 10}")
        private Integer pageSize;


        ProviderController(){
            System.out.println("ProviderController()");
        }

        @GetMapping("/provider/doGetPageSize")
        public String doGetPageSize(){
            return "page size is "+pageSize;
        }

        @GetMapping("/provider/doGetMaxThread")
        public String doGetMaxThread(){
            return "tomcat max thread id "+maxThread;
        }

        @GetMapping("/provider/doGetLogLevel")
        public String doGetLogLevel(){
            return "log level is "+logLevel;
        }

        @GetMapping("/provider/echo/{msg}")
        public String doEcho(@PathVariable String msg){
            System.out.println(msg);
            return server + " say hello "+msg;
        }
    }

}

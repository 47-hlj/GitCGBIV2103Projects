package com.cy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 当我们的主启动类使用@EnableFeignClients注解描述时，spring工程
 * 在启动时扫描@FeignClient注解描述的接口，
 * （代理类）
 */
@EnableFeignClients
@SpringBootApplication
public class ScaConsumerApplication {
    private static Logger log=LoggerFactory.getLogger(ScaConsumerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ScaConsumerApplication.class,args);
//        System.out.println("==System.out==");
        log.debug("==debug==");//debug<info<warn<error
        log.info("==info==");
        log.warn("==warn==");
        log.error("==error==");
    }

    /**
     * Springboot工程中使用此对象调用第三方服务
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 3.RestTemplate+@LoadBalanced方式
     * @Bean注解由spring提供，通常用于描述方法，用于告诉spring框架
     * 此方法的返回值要交给spring管理，类似@Controller，@Servie，@Component注解
     * 这些注解一般描述的是类
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }

    @RestController
    public class ConsumerController{
        @Value("${spring.application.name}")
        private String consumerName;
        @Autowired
        private RestTemplate restTemplate;

        /**
         * 1.RestTemplate
         * @return
         */
        @GetMapping("/consumer/doRestEcho01")
        public String doRestEcho(){
            System.out.println(consumerName);
            //定义服务提供方的地址
            String url="http://localhost:8081/provider/echo/"+consumerName;
            //调用服务提供方(sca-provider)
            System.out.println(url);
            return restTemplate.getForObject(url,String.class);
        }

        /**
         * 2.RestTemplate+LoadBalanceClient方式
         * LoadBalancerClient 此对象底层基于Ribbon实现负载均衡算法
         * LoadBalanceClient 对象在服务启动时创建好了
         */
        @Autowired
        private LoadBalancerClient loadBalancerClient;
        @GetMapping("/consumer/doRestEcho02")
        public String doRestEcho02(){
            //基于服务采用一定的负载均衡算法获取服务实例
            ServiceInstance choose = loadBalancerClient.choose("sca-provider");
            String ip=choose.getHost();
            int port=choose.getPort();
            String url="http://"+ip+":"+port+"/provider/echo/"+consumerName;
            return restTemplate.getForObject(url,String.class);
        }

        @GetMapping("/consumer/doRestEcho03")
        public String doRestEcho03(){
            String url=String.format("http://%s/provider/echo/%s","sca-provider",consumerName);
            return loadBalancedRestTemplate().getForObject(url,String.class);
        }

    }


}

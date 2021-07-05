package com.cy;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

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
        //debug<info<warn<error
//        log.debug("==debug==");
//        log.info("==info==");
//        log.warn("==warn==");
//        log.error("==error==");
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

    @Service
    public class ConsumerService{
        @SentinelResource("doConsumerService")
        public String doConsumerService(){
            //表示通过此方法访问指定资源，例如以后访问数据库
            return "do consumer service";
        }
    }

    @RestController
    public class ConsumerController{
        @Autowired
        private ConsumerService consumerService;
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

        //创建一个可实现自增自减的一个对象
        private AtomicLong atomicLong=new AtomicLong(0);
        @GetMapping("/consumer/doRestEcho03")
        public String doRestEcho03() throws InterruptedException {
//            long num=atomicLong.getAndIncrement();
//            if(num%2==0){
//                Thread.sleep(100);//模拟耗时操作//
//            }
            //流控规则中的链路限流
            //consumerService.doConsumerService();
            String url=String.format("http://%s/provider/echo/%s","sca-provider",consumerName);
            //调用服务提供方(sca-provider)
            return loadBalancedRestTemplate().getForObject(url,String.class);
        }

        //http://localhost:8090/consumer/doRestEcho04?id=15 可以基于参数0,也就是id进行限流
        @GetMapping("/consumer/doRestEcho04")
        @SentinelResource
        public String doRestEcho04(@RequestParam(required = false) Integer id,
                                   @RequestParam(required = false) String name){
            return String.format("request id=%d,name=%s ",id,name);
            //%d,%s为字符串的占位符，%d一般用做数字占位符，%s为字符串占位符
        }

    }
}

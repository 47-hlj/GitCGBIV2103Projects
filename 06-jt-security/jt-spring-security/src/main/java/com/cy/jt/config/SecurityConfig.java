package com.cy.jt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 47HLJ
 * @date 2021/7/19 8:37
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*定义密码加密匹配器对象*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        //关闭跨域攻击
        http.csrf().disable();
        //设置认证登录页面
        http.formLogin()
                //自定义登录页面
                .loginPage("/login.html")
                //.usernameParameter("username") 这里的参数名一定要与登录表单参数名相同
                //.passwordParameter("password")
                //处理登录请求的url
                .loginProcessingUrl("/auth/user/login")
                //登录成功默认页面
                .defaultSuccessUrl("/index.html")//默认
                //.successHandler(new RedirectAuthenticationSuccessHandler("http://www.tedu.cn"));
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login.html?logout");
        //设置认证规则(哪些资源必须登录后才能访问,哪些资源可以直接访问)
        http.authorizeRequests()
                .antMatchers("/login.html","/images/**","/css/**").permitAll()
                .anyRequest().authenticated();
        //异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(new DefaultAuthenticationEntryPoint())
                .accessDeniedHandler(new DefaultAccessDeniedExceptionHandler());
    }
}

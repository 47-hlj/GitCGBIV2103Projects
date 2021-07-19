package com.cy.jt.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author 47HLJ
 * @date 2021/7/19 8:41
 * 假如登录成功以后需要重定向到其它的域名地址可以这样进行实现
 */
public class RedirectAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    //重定向的url
    private final String rediectUrl;
    public RedirectAuthenticationSuccessHandler(String rediectUrl){
        this.rediectUrl=rediectUrl;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        httpServletResponse.sendRedirect(rediectUrl);
    }
}

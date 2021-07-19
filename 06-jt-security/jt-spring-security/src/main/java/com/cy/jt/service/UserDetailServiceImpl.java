package com.cy.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 47HLJ
 * @date 2021/7/19 8:34
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //1.基于用户名从数据库去查询用户信息
        //1.1)用户基本信息
        //...userDao.selectUserByUsername(username)
        if(!"jack".equals(username)) {
            throw new UsernameNotFoundException("user is not exist");
        }
        //1.2)用户权限信息(用户是什么角色,这个角色有什么权限等)
        //2.封装用户信息并返回
        //假设这个密码来自数据库
        String password=passwordEncoder.encode("123456");
        //假设这个些权限信息来自数据库
        List<GrantedAuthority> grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        "ROLE_jinpai,sys:res:create,sys:res:retrieve");
        return new User(username,password,grantedAuthorities);
    }
}

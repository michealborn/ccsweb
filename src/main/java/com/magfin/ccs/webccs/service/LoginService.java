package com.magfin.ccs.webccs.service;

import com.magfin.ccs.webccs.model.UsrUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Time 2019/4/9
 * @Author zlian
 */
@Component
public class LoginService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UsrUserService usrUserService;

    @Autowired
//    WebSecurityConfig里配置的密码加解密方法
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录工号:" + username);

        UsrUser usrUser = usrUserService.selectByPrimaryKey(username);
//        String encode = passwordEncoder.encode("123456");
        List<GrantedAuthority> authority = null;
        if(username.equals("admin")){
            authority = AuthorityUtils.commaSeparatedStringToAuthorityList("/ADMIN");
        }else{
            authority = AuthorityUtils.commaSeparatedStringToAuthorityList("/ROOT");
        }
        //从库里取出需要比较的真实用户名密码
        User user = new User(username, usrUser.getPassword(),/*数据库里的密码*/
                true,
                true,
                true,
                true,
                authority/*用户权限*/);
        return user;
    }
}

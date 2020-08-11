package com.huang.store.security;

import com.huang.store.entity.user.SecurityUser;
import com.huang.store.entity.user.User;
import com.huang.store.service.imp.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author: 黄龙
 * @date: 2020/7/20 15:09
 * @description:
 */
@Component
public class MyUserDetailService implements UserDetailsService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("======username:======"+username+"==========");
        User user = userService.getUser(username);
        System.out.println("=======从数据库中取数据取出来的user："+user+"=========");
        SecurityUser securityUser = new SecurityUser();
        if (user == null) {
            securityUser.setEnabled(true);
            securityUser.setUsername("wrong"+username);
            securityUser.setPassword("wrong");
        } else {
            logger.info("登录用户名:" + username + "    数据库密码:" + user.getPassword());
            System.out.println("========"+user.toString()+"=============");
//            String password = passwordEncoder.encode(user.getPassword());
            securityUser.setEnabled(user.isEnable());
            securityUser.setUsername(username);
            securityUser.setPassword(user.getPassword());
            String role = user.isManage()? "ROLE_ADMIN" : "ROLE_USER";
//         public static List<GrantedAuthority>
//         commaSeparatedStringToAuthorityList(String authorityString) {
//           return createAuthorityList(StringUtils.tokenizeToStringArray(authorityString, ","));
//         }
            //这里设置securityUser的角色
            securityUser.setRoles(AuthorityUtils.commaSeparatedStringToAuthorityList(role));
            System.out.println("=======MyUserDetailService==========="+securityUser.toString()+"===========");
        }
        //返回角色的信息
        return securityUser;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
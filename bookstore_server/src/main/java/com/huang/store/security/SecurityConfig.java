package com.huang.store.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.store.entity.user.SecurityUser;
import com.huang.store.entity.user.User;
import com.huang.store.service.imp.UserService;
import com.huang.store.util.JwtTokenUtil;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 黄龙
 * @date: 2020/7/29 12:37
 * @description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
//    auth.userDetailsService(myUserDetailService)将我们自己定义的myUserDetailService
//    注入到SpringSecurity的安全上下文中去，SpringSecurity早自己有一个默认的UserDetailsService,这里将它替换掉了
    //同样地，这里将我们自己定义的加密工具类替换掉原先默认的加密替换类
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().withUser("黄小龙").password("$2a$10$vEBSLD7Wy7UsF2raEtZa0e5uUg0HGOosxEycfLdJj4LtkMiQIZXpm").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/states","/user/switch","/book/modify").hasAnyRole("ADMIN")
                .antMatchers("/user/init","/book/get","/user/code","/user/signup","/book/detail","/book/search","/images/*","/mail").permitAll()
                .antMatchers("/book/*", "/register","/sort/*","/topic/*","/user/register","/user/accountVerify")// 对登录注册要允许匿名访问
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
                .permitAll()
//                .antMatchers("/**")//测试时全部运行访问
//                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                HttpSession session = request.getSession();
                session.removeAttribute("loginState");
                out.write(new ObjectMapper().writeValueAsString( "登出成功"));
                out.flush();
                out.close();
            }
        })
                .and().csrf().disable()
                // 开启跨域
                .cors().and();
        // 禁用缓存
        http.headers().cacheControl();

        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);

        //添加登陆验证过滤器
        http.addFilterAt(CAFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    CustomAuthenticationFilter CAFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                System.out.println("=============登录成功===============");
                //从authentication中取出SecurityUser的信息
                SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
                String token = jwtTokenUtil.generateToken(securityUser);
                token = "Bearer"+token;
                System.out.println("====token:====="+token+"=====");
                resp.setHeader("Authorization", token);
                resp.setHeader("Access-control-Expose-Headers", "Authorization");
                System.out.println("从authentication中取出的用户信息"+securityUser.toString());
                User user = new User();
                user.setAccount(securityUser.getUsername());
                boolean isManage = userService.getUser(user.getAccount()).isManage();
                if(isManage){
                    user.setManage(true);
                }else {
                    user.setManage(false);
                }
                Map<String,Object> map = new HashMap<>();
                map.put("user",user);
                String json = JSON.toJSONString(ResultUtil.resultSuccess(map));
                out.print(json);
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                System.out.println("=============登录失败=================");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString("登录失败"));
                out.flush();
                out.close();
            }
        });
        //authenticationManagerBean()为WebSecurityConfigurerAdapter中的一个方法，
        // 这个方法的返回值为AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }
}

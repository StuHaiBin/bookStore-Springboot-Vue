package com.huang.store.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author: 黄龙
 * @date: 2020/7/29 12:37
 * @description:
 * UsernamePasswordAuthenticationFilter是AbstractAuthenticationProcessingFilter
 * 针对使用用户名和密码进行身份验证而定制化的一个过滤器。
 * 其添加是在调用http.formLogin()时作用，默认的登录请求pattern为"/login"，并且为POST请求。
 * 当我们登录的时候，也就是匹配到loginProcessingUrl，
 * 这个过滤器就会委托认证管理器authenticationManager来验证登录
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //这里是得到前端传来的用户的账号密码，将其注入到过滤器中，终于之后的验证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //ObjectMapper是jackson的主要类
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                //反序列化，将json对象转换为java对象
                //其实这里也可不必反序列化，如果是token的话，直接在这里获取token的值进行解析就可以了
                Map<String,String> authenticationBean = mapper.readValue(is, Map.class);
                //authenticationBean.get("account")为得到用户的账号
                //authenticationBean.get("password")为得到用户的密码
                System.out.println("=====authenticationBean.get(\"username\")====="+authenticationBean.get("account")+"========");
                System.out.println("=====authenticationBean.get(\"password\")====="+authenticationBean.get("password")+"========");
                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.get("account"), authenticationBean.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
                //如果出现异常，则将用户的账号密码设置为空
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
                //this.getAuthenticationManager()返回一个authenticationManager，
                //然后这个authenticationManager调用authenticate方法，
                // 这个方法返回的是一个填充完整信息的Authentication，
                //最后在SecurityConfig类中，将这个认证信息放进安全上下文中
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        else {
            return super.attemptAuthentication(request, response);
        }
    }
}
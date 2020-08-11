package com.huang.store.mapper;

import com.huang.store.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int addUser(User user);//添加用户
    User getUser(String account);//通过账号得到用户
    List<User> getUsersByPage(int page,int pageSize);//按页得到用户列表
    int count();//得到用户总数
    int updateUser(User user);//更新用户信息
    int updatePwd(String account,String newPassword);//更新用户密码
    int updateImg(String account,String imgUrl);//更新用户头像
}

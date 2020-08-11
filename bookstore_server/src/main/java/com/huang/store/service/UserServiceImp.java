package com.huang.store.service;

import com.huang.store.entity.user.User;
import com.huang.store.mapper.UserMapper;
import com.huang.store.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("firstUser")
public class UserServiceImp implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int addUser(User user) {
        int result = userMapper.addUser(user);
        return result;
    }

    @Override
    public User getUser(String account) {
        return userMapper.getUser(account);
    }


    @Override
    public List<User> getUsersByPage(int page, int pageSize) {
        page = (page-1)*pageSize;
        return userMapper.getUsersByPage(page,pageSize);
    }

    @Override
    public int count() {
        return userMapper.count();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updatePwd(String account,String newPassword) {
        return userMapper.updatePwd(account,newPassword);
    }

    @Override
    public int updateImg(String account, String ImgUrl) {
        return userMapper.updateImg(account,ImgUrl);
    }

    @Override
    public boolean isExist(String account, String pwd) {
        User user = userMapper.getUser(account);
        if(pwd.equals(user.getPassword())){
            return true;
        }
        return false;
    }

}

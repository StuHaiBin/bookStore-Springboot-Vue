package com.huang.store.controller;

import com.huang.store.entity.user.Address;
import com.huang.store.entity.user.User;
import com.huang.store.service.UserServiceImp;
import com.huang.store.service.imp.AddressService;
import com.huang.store.service.imp.UserService;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    @Qualifier("firstUser")
    UserService userService;

    @Autowired
    @Qualifier("firstAddress")
    AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//  ============= 注册 =====================

    /**
     * 用户注册
     * @param account
     * @param password
     * @return
     */
    @GetMapping(value = "/user/register")
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam(value = "account") String account,
                                       @RequestParam(value = "password") String password){
        System.out.println("======开始注册=======");
        User user1 = userService.getUser(account);
        if(user1 != null){
            System.out.println("该账号已经被注册！");
            return ResultUtil.resultCode(500,"该账号已被注册！");
        }
        System.out.println("该账号未被注册");
        User user = new User();
        user.setAccount(account);
        user.setPassword(passwordEncoder.encode(password));
        user.setManage(false);
        Date date = new Date();

        if(userService.addUser(user)>0){
            return ResultUtil.resultCode(200,"注册成功");
        }
        return ResultUtil.resultCode(500,"注册失败");
    }

//    =====================对用户地址的操作=====================================
    /**
     *
     * @return 某个用户的地址列表
     */
    @ResponseBody
    @GetMapping("/getUserAddress")
    public Map<String,Object> getUserAddress(@RequestParam("account")String account){
        Map<String,Object> map = new HashMap<>();
        System.out.println("=============="+account+"===========");
        List<Address> addressList = addressService.addressList(account);
        for(Address address:addressList){
            System.out.println("======"+address.toString()+"========");
        }
        map.put("addressList",addressList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 添加地址
     * @param address
     * @return
     */
    @ResponseBody
    @PostMapping("/addUserAddress")
    public Map<String,Object> addUserAddress(@RequestBody Address address){
        int result = addressService.addAddress(address);
        if(result>0){
            return ResultUtil.resultCode(200,"添加地址成功");
        }
        return ResultUtil.resultCode(500,"添加地址失败");
    }


    /**
     * 删除地址
     * @param id
     * @return 删除信息
     */
    @ResponseBody
    @GetMapping("/delUserAddress")
    public Map<String,Object> delUserAddress(@RequestParam("id")int id){
        int result = addressService.deleteAddress(id);
        if(result>0){
            return ResultUtil.resultCode(200,"删除地址成功");
        }
        return ResultUtil.resultCode(500,"删除地址失败");
    }

    /**
     * 修改地址
     * @param address
     * @return 修改信息
     */
    @ResponseBody
    @PostMapping("/modifyUserAddress")
    public Map<String,Object> modifyUserAddress(@RequestBody Address address){
        int result = addressService.modifyAddress(address);
        if(result>0){
            return ResultUtil.resultCode(200,"修改地址成功");
        }
        return ResultUtil.resultCode(500,"修改地址失败");
    }

//    =============================用户列表管理==================================

    /**
     * 按页得到用户列表
     * @param page
     * @param pageSize
     * @return
     */
    @ResponseBody
    @GetMapping("/getUserList")
    public Map<String,Object> getUserList(@RequestParam(value = "page")int page,
                                          @RequestParam(value = "pageSize")int pageSize){
        List<User> userList = userService.getUsersByPage(page, pageSize);
        int total = userService.count();
        Map<String,Object> map = new HashMap<>();
        map.put("userList",userList);
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 修改用户的状态(禁用或没被禁用)
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @GetMapping("/modifyUserStatus")
    public Map<String,Object> modifyUserStatus(@RequestParam(value = "id")int id,
                                               @RequestParam(value = "status")boolean status){
        User user = new User();
        user.setId(id);
        user.setEnable(true);
        System.out.println("=======id======:"+id);
        System.out.println("=======status======:"+status);
        if(userService.updateUser(user)>0){
            return ResultUtil.resultCode(200,"修改成功");
        }
        return ResultUtil.resultCode(500,"修改失败");
    }

//    =================================用户个人信息管理================================

    /**
     * 得到一个用户的个人信息
     * @param account
     * @return
     */
    @ResponseBody
    @GetMapping("/getUserInfo")
    public Map<String,Object> getUserInfo(@RequestParam(value = "account")String account){
        User user = userService.getUser(account);
        Map<String,Object> map = new HashMap<>();
        map.put("user",user);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 修改用户的密码
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ResponseBody
    @GetMapping("/modifyUserPwd")
    public Map<String,Object> modifyUserPwd(@RequestParam(value = "account")String account,
                                            @RequestParam(value = "oldPassword")String oldPassword,
                                            @RequestParam(value = "newPassword")String newPassword){
        User user = userService.getUser(account);
        System.out.println("=======user account========:"+account+"=========");
        System.out.println("=====开始修改密码======");
        System.out.println("=====前端传过来的旧密码：====："+oldPassword+"=====");
        System.out.println("=====数据库中的密码：================"+user.getPassword()+"====");
        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            System.out.println("====说明密码匹配正确====");
            newPassword = passwordEncoder.encode(newPassword);
            if(userService.updatePwd(account,newPassword)>0){
                return ResultUtil.resultCode(200,"修改密码成功");
            }
        }
        return ResultUtil.resultCode(500,"修改密码错误!请确认你输入了原先正确的密码");
    }
}

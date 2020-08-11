package com.huang.store.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
/**
 * @author: 黄龙
 * @date: 2020/7/30 17:56
 * @description: 用户类
 */
public class User {
    private int id;//用户编号
    private String account;//用邮箱注册
    private String password;
    private String name;
    private String gender;
    private String imgUrl;
    private String info;//个人简介
    private boolean manage;//是否为管理员
    private boolean enable;//是否禁用
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp registerTime;//注册时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isManage() {
        return manage;
    }

    public void setManage(boolean manage) {
        this.manage = manage;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", info='" + info + '\'' +
                ", manage=" + manage +
                ", enable=" + enable +
                ", registerTime=" + registerTime +
                '}';
    }
}

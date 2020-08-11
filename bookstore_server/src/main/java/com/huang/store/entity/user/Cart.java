package com.huang.store.entity.user;

import java.sql.Timestamp;
/**
 * @author: 黄龙
 * @date: 2020/7/22 21:46
 * @description: 购物车类
 */
public class Cart {
    private String account;
    private int id;
    private Integer num;
    private Timestamp addTime;//添加到购物车的时间

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "account='" + account + '\'' +
                ", id='" + id + '\'' +
                ", num=" + num +
                ", addTime=" + addTime +
                '}';
    }
}

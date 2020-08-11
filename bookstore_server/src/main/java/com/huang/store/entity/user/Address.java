package com.huang.store.entity.user;

/**
 * @author: 黄龙
 * @date: 2020/7/23 13:30
 * @description: 地址类
 */
public class Address {
    private int id;//编号
    private String account;
    private String name;//收货人姓名
    private String phone;//收货人电话
    private String addr;//具体地址
    private String label;//标签
    private boolean off;//是否被设置为删除(这里的删除不是真的删除，只是这个地址不在出现在用户的地址列表中)

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isOff() {
        return off;
    }

    public void setOff(boolean off) {
        this.off = off;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", label='" + label + '\'' +
                ", off=" + off +
                '}';
    }
}

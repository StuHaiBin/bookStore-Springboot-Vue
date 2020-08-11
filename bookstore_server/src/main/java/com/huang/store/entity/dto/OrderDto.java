package com.huang.store.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huang.store.entity.order.Expense;
import com.huang.store.entity.user.Address;

import java.util.Date;
import java.util.List;

/**
 * @author: 黄龙
 * @date: 2020/7/20 15:49
 * @description: 前后端交互的订单类
 */
public class OrderDto {
    private int id;//编号
    private String orderId;//订单号
    private String account;//账户
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderTime;//下单时间
    private Date shipTime;//发货时间
    private Date getTime;//收货时间
    private Date evaluateTime;//评价时间
    private Date closeTime;//结束时间
    private Date confirmTime;//自动确认收货的时间 默认14天
    private String orderStatus;//订单状态
    private String logisticsNum;//物流单号
    private List<OrderDetailDto> OrderDetailDtoList;//订单
    private Expense expense;//订单费用明细
    private Address address;//收货地址
    private List<String> coverImgList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public List<OrderDetailDto> getOrderDetailDtoList() {
        return OrderDetailDtoList;
    }

    public void setOrderDetailDtoList(List<OrderDetailDto> orderDetailDtoList) {
        OrderDetailDtoList = orderDetailDtoList;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getCoverImgList() {
        return coverImgList;
    }

    public void setCoverImgList(List<String> coverImgList) {
        this.coverImgList = coverImgList;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", account='" + account + '\'' +
                ", orderTime=" + orderTime +
                ", shipTime=" + shipTime +
                ", getTime=" + getTime +
                ", evaluateTime=" + evaluateTime +
                ", closeTime=" + closeTime +
                ", confirmTime=" + confirmTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", logisticsNum='" + logisticsNum + '\'' +
                ", OrderDetailDtoList=" + OrderDetailDtoList +
                ", expense=" + expense +
                ", address=" + address +
                '}';
    }
}

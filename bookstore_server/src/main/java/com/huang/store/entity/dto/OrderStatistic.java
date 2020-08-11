package com.huang.store.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author: 黄龙
 * @date: 2020/7/30 13:50
 * @description: 订单统计类,用于图表显示的类
 */
public class OrderStatistic {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp orderTime;//下单时间
    private int count;//下单总数
    private double amount;//下单总价

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderStatistic{" +
                "orderTime=" + orderTime +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }
}

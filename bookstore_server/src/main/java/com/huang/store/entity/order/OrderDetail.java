package com.huang.store.entity.order;

import java.sql.Timestamp;

/**
 * @author: 黄龙
 * @date: 2020/7/23 20:06
 * @description: 订单明细
 */
public class OrderDetail {
    private String orderId;//订单号
    private int bookId; //图书id
    private int num;//某本图书的下单数量
    private double price;//下单时候图书的单价

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", bookId=" + bookId +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}

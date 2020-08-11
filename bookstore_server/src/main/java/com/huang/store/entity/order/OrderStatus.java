package com.huang.store.entity.order;

/**
 * @author: 黄龙
 * @date: 2020/7/25 22:29
 * @description: 订单状态
 */
public class OrderStatus {
    private int id;//编号
    private String statusName;//状态名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}

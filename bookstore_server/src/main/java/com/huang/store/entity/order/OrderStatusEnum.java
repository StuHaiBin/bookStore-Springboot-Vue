package com.huang.store.entity.order;

/**
 * @author: 黄龙
 * @date: 2020/7/22 9:37
 * @description: 订单状态枚举类
 */
public enum OrderStatusEnum {
    SUBMIT(1, "已付款"),//已经提交
    SHIPPED(2, "已发货"),
    RECEIVED(3, "已收货"),
    EVALUATED(4, "已评价"),
    CLOSE(5,"已关闭"),//未支付或者退货成功了
    BEDELETE(6,"被删除");//用户层面的被删除,

    private int index;
    private String name;

    OrderStatusEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

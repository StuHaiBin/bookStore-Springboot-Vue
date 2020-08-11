package com.huang.store.entity.order;

import java.sql.Timestamp;
/**
 * @author: 黄龙
 * @date: 2020/6/5 12:03
 * @description: 优惠券
 */
public class Coupon {
    private int id;//编号，自动增加
    private String name;//名称
    private Integer issuesNum;//发行数量
    private Timestamp issuesTime;//发行时间
    private Double money;//面额
    private Integer limitNum;//每人限领张数
    private Integer thresholdMoney;//使用门槛，(满多少可用)
    private Timestamp startTime;//起效时间
    private Timestamp endTime;//终止时间
    private String remarks;//备注
    private String range;//使用范围，全场通用，指定分类，指定商品
}

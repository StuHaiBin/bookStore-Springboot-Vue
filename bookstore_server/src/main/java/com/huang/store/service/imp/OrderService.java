package com.huang.store.service.imp;

import com.huang.store.entity.dto.OrderDetailDto;
import com.huang.store.entity.dto.OrderDto;
import com.huang.store.entity.dto.OrderInitDto;
import com.huang.store.entity.dto.OrderStatistic;
import com.huang.store.entity.order.Order;
import com.huang.store.entity.order.OrderDetail;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author: 黄龙
 * @date: 2020/7/20 17:53
 * @description:
 */
@Service
public interface OrderService {

    boolean addOrder(OrderInitDto orderInitDto);//添加订单 其中涉及添加总的订单表、订单明细表、费用信息表、还有减去库存的操作等

    int delOrder(int id);//删除订单  这里涉及了多表删除  并且只能删除已经完成了的订单

    int userDelOrder(int id);//用户删除订单,这里的删除不是真正的删除，而是设置删除标志位为true

    int batchDelOrder(List<Integer> item);//批量删除 这里涉及多表联合删除

    int modifyOrderStatus(int id, String orderStatus);//修改订单的状态

    int deliverBook(int id,int logisticsCompany,String logisticsNum);//发货

    OrderDto findOrderDto(int id);//得到某个订单的所有信息明细

    List<OrderDetailDto> findOrderDetailDtoList(String orderId);//批量得到指定订单所有图书的明细信息

    //按页得到所有订单的明细 这里需要根据userId判断是否是管理员
    List<OrderDto> orderDtoList(String userId,int pageNum,int pageSize,String orderStatus,boolean beUserDelete);

    //得到订单的总数 id值的意义和得到订单明细列表方法id一样
    int count(String userId,String orderStatus,boolean beUserDelete);

    //得到订单的统计数据
    List<OrderStatistic> getOrderStatistic(String beginDate, String endDate) throws ParseException;

}

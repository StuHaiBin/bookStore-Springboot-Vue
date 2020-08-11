package com.huang.store.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.huang.store.entity.dto.*;
import com.huang.store.entity.order.Expense;
import com.huang.store.entity.user.Address;
import com.huang.store.service.imp.AddressService;
import com.huang.store.service.imp.BookService;
import com.huang.store.service.imp.CartService;
import com.huang.store.service.imp.OrderService;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: 黄龙
 * @date: 2020/7/22 20:25
 * @description:
 */
@Controller
@ResponseBody
public class OrderController {

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;

    @Autowired
    @Qualifier("firstCart")
    CartService cartService;

    @Autowired
    @Qualifier("firstAddress")
    AddressService addressService;

    @Autowired
    @Qualifier("orderService")
    OrderService orderService;

    /**
     * 初始化订单，得到用户购买的图书集合，得到费用信息，并返回前端费用信息和图书集合
     * @param ids 用户预购买的时候的图书id集合
     * @param from from用来标记下订单是从哪个页面操作的 0代表详情页 1代表购物车
     * @return
     */
    @GetMapping("/initOrder")
    public Map<String,Object> initOrder(@RequestParam(value = "ids") int[] ids,
                                        @RequestParam(value = "from") int from,
                                        @RequestParam(value = "account") String account){
        for(int i=0;i<ids.length;i++){
            System.out.println("===ids[i]========"+ids[i]+"==============");
        }
        Map<String,Object> map = new HashMap<>();
        Expense expense = new Expense();
        OrderInitDto orderInitDto = new OrderInitDto();
        List<OrderBookDto> batchBookList = new ArrayList<>();
        if(from==1){//从购物车点击提交的
            batchBookList = bookService.getBatchBookList(ids);
            for(int i=0;i<batchBookList.size();i++){
                int bookCount = cartService.getBookCount(account, batchBookList.get(i).getId());
                batchBookList.get(i).setNum(bookCount);
                System.out.println("====batchBookList.get(i).getNum():======"+batchBookList.get(i).getNum()+"======");
            }
            cartService.delBatchProduct(account,ids);//删除购物车中的图书
        }else {//从详情页点击提交的
            batchBookList = bookService.getOneBookList(ids);
            batchBookList.get(0).setNum(1);
        }
        for(int i=0;i<batchBookList.size();i++){
            String img = bookService.getBookCover(batchBookList.get(i).getIsbn());
            batchBookList.get(i).setCoverImg(img);
        }

        Double productTotalMoney = 0.0;
        for(OrderBookDto orderBookDto :batchBookList){
            productTotalMoney = productTotalMoney + orderBookDto.getPrice()*orderBookDto.getNum();//得到订单总价
        }
        expense.setProductTotalMoney(productTotalMoney);//商品总价
        expense.setFreight(0);
        expense.setCoupon(0);
        expense.setActivityDiscount(0);
        expense.setAllPrice(productTotalMoney);//订单总金额
        expense.setFinallyPrice(productTotalMoney);//最终实付金额
        List<Address> addressList = addressService.addressList(account);//得到某个用户的地址列表
        orderInitDto.setAddressList(addressList);
        orderInitDto.setBookList(batchBookList);
        orderInitDto.setExpense(expense);
        orderInitDto.setAccount(account);
        System.out.println("============account:==========="+account+"============");
        map.put("orderInitDto",orderInitDto);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 添加订单
     * @param orderInitDto
     * @return
     */
    @PostMapping("/addOrder")
    public Map<String,Object> addOrder(@RequestBody OrderInitDto orderInitDto){
        if(!orderService.addOrder(orderInitDto)){
            return ResultUtil.resultCode(500,"提交订单失败");
        }
        return ResultUtil.resultCode(200,"提交订单成功");
    }


    /**
     * 得到管理员可以查看的订单信息列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getAdminOrderList")
    public Map<String,Object> egtOrderList(@RequestParam("page")int page,
                                       @RequestParam("pageSize")int pageSize){
        System.out.println("=========请求到达获取订单接口===========");
        List<OrderDto> orderDtoList = orderService.orderDtoList("", page, pageSize,"",false);
        Map<String,Object> map= new HashMap<>();
        map.put("orderDtoList",orderDtoList);
        int total = orderService.count("","",false);
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到某个订单的全部明细信息
     * @param id
     * @return
     */
    @GetMapping("/getOrderDto")
    public Map<String,Object> getOrderDto(@RequestParam("id")int id){
        OrderDto orderDto = orderService.findOrderDto(id);
        List<OrderDetailDto> orderDetailDtoList = orderService.findOrderDetailDtoList(orderDto.getOrderId());
        for(int i=0;i<orderDetailDtoList.size();i++){
            String img = bookService.getBookCover(orderDetailDtoList.get(i).getBook().getisbn());
            orderDetailDtoList.get(i).getBook().setCoverImg(img);
            System.out.println("=======orderDetailDtoList.size():====="+orderDetailDtoList.size()+"============");
        }
        orderDto.setOrderDetailDtoList(orderDetailDtoList);
        Map<String,Object> map= new HashMap<>();
        map.put("orderDto",orderDto);
        return ResultUtil.resultSuccess(map);
    }


    /**
     * 删除订单
     * @param id
     * @return
     */
    @GetMapping("/delOrder")
    public Map<String,Object> delOrder(@RequestParam("id")int id){
        System.out.println("============="+id+"=================");
        if(orderService.delOrder(id)>0){
            return ResultUtil.resultCode(200,"删除订单成功");
        }
        return ResultUtil.resultCode(500,"删除订单失败");
    }

    @GetMapping("/deliverOrder")
    public Map<String,Object> delOrdr(@RequestParam("id")int id,
                                      @RequestParam("logisticsCompany")int logisticsCompany,
                                      @RequestParam("logisticsNum")String logisticsNum){
        System.out.println("============="+id+"=================");
        if(orderService.deliverBook(id,logisticsCompany,logisticsNum)>0){
            return ResultUtil.resultCode(200,"发货成功");
        }
        return ResultUtil.resultCode(500,"发货失败");
    }

    @GetMapping("/getUserOrderList")
    public Map<String,Object> getUserOrderList(@RequestParam("account")String account,
                                               @RequestParam("page")int page,
                                               @RequestParam("pageSize")int pageSize,
                                               @RequestParam("orderStatus")String orderStatus,
                                               @RequestParam("beUserDelete")boolean beUserDelete){
        System.out.println("=========orderStatus,beUserDelete===========:"+orderStatus+" "+beUserDelete+"=========");
        List<OrderDto> orderDtoList = orderService.orderDtoList(account, page, pageSize,orderStatus,beUserDelete);
        for(int i=0;i<orderDtoList.size();i++){
            List<OrderDetailDto> orderDetailDtoList = orderService.findOrderDetailDtoList(orderDtoList.get(i).getOrderId());
            List<String> coverImgList = new ArrayList<>();
            for(int j=0;j<orderDetailDtoList.size() && j<5;j++){
                System.out.println("======orderDetailDtoList.get(j)====:"+orderDetailDtoList.get(j)+"=========");
                String img = bookService.getBookCover(orderDetailDtoList.get(j).getBook().getisbn());
                coverImgList.add(img);
            }
            System.out.println("=====coverImgList.size()====="+coverImgList.size()+"===================");
            orderDtoList.get(i).setCoverImgList(coverImgList);
        }
        Map<String,Object> map= new HashMap<>();
        map.put("orderDtoList",orderDtoList);
        int total = orderService.count(account,orderStatus,beUserDelete);
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 修改订单的状态
     * @param id
     * @param orderStatus
     * @return
     */
    @GetMapping("/modifyOrderStatus")
    public Map<String,Object> modifyOrderStatus(@RequestParam("id")int id,
                                      @RequestParam("orderStatus")String orderStatus){
        System.out.println("========确认收货===="+id);
        if(orderService.modifyOrderStatus(id,orderStatus)>0){
            return ResultUtil.resultCode(200,"操作成功");
        }
        return ResultUtil.resultCode(500,"操作失败");
    }


    /**
     * 返回日期筛选后的订单数据
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/order/date", method = RequestMethod.GET)
    public Map<String,Object> dateFilter(@RequestParam("beginDate")String beginDate,
                                         @RequestParam("endDate")String endDate) throws ParseException {
        // 将结束日期+1 便于sql操作
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = format.parse(beginDate);
        Date date1 = format.parse(endDate);
//        try {
////            Calendar calendar = Calendar.getInstance();
////            calendar.setTime(date1);
////            calendar.add(Calendar.DAY_OF_MONTH, 1);
////            date1 = calendar.getTime();
////        } catch (Exception e){
////            System.out.println(e);
////        }
        Map<String,Object> map = new HashMap<>();
        List<OrderStatistic> orderStatistic = orderService.getOrderStatistic(beginDate, endDate);
        map.put("orderStatistic",orderStatistic);
        return ResultUtil.resultSuccess(map);
    }


}

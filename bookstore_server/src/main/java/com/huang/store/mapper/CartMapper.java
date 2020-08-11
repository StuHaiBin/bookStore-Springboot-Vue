package com.huang.store.mapper;

import com.huang.store.entity.dto.CartBookDto;
import com.huang.store.entity.user.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int addProduct(Cart cart); //添加图书
    int existProduct(String account,int id);//判断购物车中是否已经存在某本图书
    int deleteProduct(String account,int id);//删除图书
    int delBatchProduct(String account,int[] ids);//批量删除图书
    int modifyProductNum(Cart cart);//修改图书
    List<CartBookDto> getCartsByPage(String account, int page, int pageSize);//按页得到某账号的购物车信息
    int count(String account);//得到某人的购物车商品总数
    int getBookCount(String account,int id);//得到某本书在购物车中的数量
}

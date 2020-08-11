package com.huang.store.service;

import com.huang.store.entity.dto.CartBookDto;
import com.huang.store.entity.user.Cart;
import com.huang.store.mapper.CartMapper;
import com.huang.store.service.imp.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("firstCart")
public class CartServiceImp implements CartService {
    @Autowired
    CartMapper cartMapper;

    @Override
    public int addProduct(String account,int id,int num) {
        Cart cart = new Cart();
        cart.setAccount(account);
        cart.setAddTime(new Timestamp(new Date().getTime()));
        cart.setId(id);
        cart.setNum(num);
        int result = cartMapper.addProduct(cart);
        return result;
    }

    @Override
    public int existProduct(String account, int id) {
        return cartMapper.existProduct(account, id);
    }

    @Override
    public int deleteProduct(String account, int id) {
        int result = cartMapper.deleteProduct(account,id);
        return result;
    }

    @Override
    public int delBatchProduct(String account,int[] ids) {
        return cartMapper.delBatchProduct(account,ids);
    }

    @Override
    public int modifyProductNum(String account,int id,int num) {
        Cart cart = new Cart();
        cart.setNum(num);
        cart.setId(id);
        cart.setAccount(account);
        int result = cartMapper.modifyProductNum(cart);
        return result;
    }


    @Override
    public List<CartBookDto> getCartsByPage(String account, int page, int pageSize) {
        page = (page-1)*pageSize;
        return cartMapper.getCartsByPage(account,page,pageSize);
    }

    @Override
    public int count(String account) {
        return cartMapper.count(account);
    }

    @Override
    public int getBookCount(String account, int id) {
        return cartMapper.getBookCount(account, id);
    }
}

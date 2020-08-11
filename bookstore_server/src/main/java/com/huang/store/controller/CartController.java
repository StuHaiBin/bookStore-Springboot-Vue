package com.huang.store.controller;

import com.huang.store.entity.dto.CartBookDto;

import com.huang.store.service.imp.BookService;
import com.huang.store.service.imp.CartService;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 黄龙
 * @date: 2020/7/23 20:18
 * @description:
 */
@Controller
@ResponseBody
public class CartController {

    @Autowired
    @Qualifier("firstCart")
    CartService cartService;

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;

    /**
     * 添加图书到购物车
     * @param id
     * @param num
     * @return
     */
    @GetMapping("/addCart")
    public Map<String,Object> addCart(@RequestParam("id")int id,
                                      @RequestParam("num")int num,
                                      @RequestParam("account")String account){
        if(cartService.existProduct(account,id)>0){//如果购物车中已经存在图书
            return ResultUtil.resultCode(500,"购物车中已经存在该图书");
        }
        if(cartService.addProduct(account,id,num)>0){
            return ResultUtil.resultCode(200,"添加到购物车成功");
        }
        return ResultUtil.resultCode(500,"添加到购物车失败");
    }

    /**
     * 删除某人购物车中的某本图书
     * @param id
     * @param account
     * @return
     */
    @GetMapping("/delCart")
    public Map<String,Object> addCart(@RequestParam("id")int id,
                                      @RequestParam("account")String account){
        if(cartService.deleteProduct(account,id)>0){
            return ResultUtil.resultCode(200,"删除成功");
        }
        return ResultUtil.resultCode(500,"删除失败");
    }

    /**
     * 批量删除购物车
     * @param ids
     * @param account
     * @return
     */
    @GetMapping("/batchDelCart")
    public Map<String,Object> addCart(@RequestParam("ids")int[] ids,
                                      @RequestParam("account")String account){
        if(cartService.delBatchProduct(account,ids)>0){
            return ResultUtil.resultCode(200,"删除成功");
        }
        return ResultUtil.resultCode(500,"删除失败");
    }

    /**
     * 这里的修改购物车只是修改购物车的数量
     * @param id
     * @param num
     * @param account
     * @return
     */
    @GetMapping("/modifyCart")
    public Map<String,Object> modifyCart(@RequestParam("id")int id,
                                      @RequestParam("num")int num,
                                      @RequestParam("account")String account){
        if(cartService.modifyProductNum(account,id,num)>0){
            return ResultUtil.resultCode(200,"修改成功");
        }
        return ResultUtil.resultCode(500,"修改失败");
    }


    /**
     * 得到购物车图书列表
     * @param account
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getCartList")
    public Map<String,Object> getCartList(@RequestParam("account")String account,
                                          @RequestParam("page")int page,
                                          @RequestParam("pageSize")int pageSize){
        Map<String,Object> map = new HashMap<>();
        List<CartBookDto> cartBookDtoList = cartService.getCartsByPage(account, page, pageSize);
        for(int i=0;i<cartBookDtoList.size();i++){
            String img = bookService.getBookCover(cartBookDtoList.get(i).getIsbn());
            cartBookDtoList.get(i).setCoverImg(img);
        }
        map.put("cartBookDtoList",cartBookDtoList);
        return ResultUtil.resultSuccess(map);
    }

}

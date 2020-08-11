package com.huang.store.controller;

import com.huang.store.entity.book.BookSort;
import com.huang.store.entity.dto.SortResponse;
import com.huang.store.service.imp.SortService;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@ResponseBody
@RequestMapping(value = "/sort")
public class SortController {

    @Autowired
    @Qualifier("firstSort")
    SortService sortService;

    /**
     * 添加图书的分类
     * @param bookSort
     * @return
     */
    @PostMapping("/addBookSort")
    public Map<String,Object> addBookSort(@RequestBody BookSort bookSort){
        System.out.println(bookSort.toString());
        if(sortService.addSort(bookSort)>0){
            return ResultUtil.resultCode(200,"添加图书分类成功");
        }
        return ResultUtil.resultCode(500,"添加图书分类失败");
    }

    /**
     * 得到图书的分类
     * @param upperName
     * @param sortName
     * @return
     */
    @GetMapping("/getBookSort")
    public Map<String,Object> getBookSort(@RequestParam(value = "upperName")String upperName,
                                          @RequestParam(value = "sortName")String sortName){
        System.out.println(upperName+sortName);
        BookSort bookSort=new BookSort();
        bookSort = sortService.getBookSort(upperName, sortName);
        Map<String,Object> map = new HashMap<>();
        map.put("bookSort",bookSort);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 修改图书的分类
     * @param bookSort
     * @return
     */
    @PostMapping("/modifyBookSort")
    public Map<String,Object> modifyBookSort(@RequestBody BookSort bookSort) {
        if(bookSort.getUpperName().equals("无")){//如果修改后上一级为无
            BookSort bookSort1=sortService.getBookSortById(bookSort.getId());//得到修改分类原来的值
            System.out.println(bookSort1.toString());
            if(!bookSort1.getSortName().equals(bookSort.getSortName())){//如果修改分类的的原来的值不等于现
                sortService.modifySortUpperName(bookSort1.getSortName(),bookSort.getSortName());
            }
        }
        if (sortService.modifySort(bookSort) > 0) {
            return ResultUtil.resultCode(200, "修改图书分类成功");
        }
        return ResultUtil.resultCode(500, "修改图书分类失败");
    }

    /**
     * 按页到一级分类集合
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getFirstSortList")
    public Map<String,Object> getFirstSortList(@RequestParam(value = "page")int page,
                                              @RequestParam(value = "pageSize")int pageSize){
        Map<String,Object> map=new HashMap<>();
        List<BookSort> bookSortList = sortService.getFirstSorts(page, pageSize);
        map.put("bookSortList",bookSortList);
        int total=sortService.getFirstCount();
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 按页得到二级分类集合
     * @param upperName
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/getSecondSortList")
    public Map<String,Object> getSecondSortList(@RequestParam(value = "upperName")String upperName,
                                                @RequestParam(value = "page")int page,
                                                @RequestParam(value = "pageSize")int pageSize){
        Map<String,Object> map=new HashMap<>();
        List<BookSort> bookSortList = sortService.getSecondSorts(upperName, page, pageSize);
        map.put("bookSortList",bookSortList);
        int total=sortService.getSecondCount(upperName);
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }


    /**
     * 得到所有一级分类的名称
     * @return
     */
    @GetMapping("/getUpperNames")
    public Map<String,Object> getUpperSorts(){
        Map<String,Object> map=new HashMap<>();
        List<String> upperNames=sortService.getUpperSorts();
        upperNames.add("无");
        int size = upperNames.size();
        Collections.swap(upperNames, size-1, 0);
        map.put("upperNames",upperNames);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到分类的一级分类和二级分类，封装后放给前端
     * @return
     */
    @GetMapping("/getBookSortList")
    public Map<String,Object> getBookSortList() {
        Map<String, Object> map = new HashMap<>();
        List<SortResponse> sortResponseList = new ArrayList<>();
        List<BookSort> bookSortList = sortService.getAllFirSorts();//得到所有一级分类
        for(int i=0;i<bookSortList.size();i++){
            List<BookSort> children = sortService.getSecondSortList(bookSortList.get(i).getSortName());
            SortResponse sortResponse = new SortResponse();
            sortResponse.setUpperSort(bookSortList.get(i));
            sortResponse.setChildren(children);
            sortResponseList.add(sortResponse);
        }
        map.put("sortResponseList",sortResponseList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 删除一级分类
     * @param sortName
     * @return
     */
    @GetMapping("/delFirstSort")
    public Map<String,Object> deleteSort(@RequestParam(value = "sortName")String sortName){
        System.out.println(sortName);
        if(sortService.deleteFirSort(sortName)>0){
            return ResultUtil.resultCode(200,"删除分类成功");
        }
        return ResultUtil.resultCode(500,"删除分类失败");
    }

    /**
     * 删除二级分类
     * @param sortName
     * @param upperName
     * @return
     */
    @GetMapping("/delSecondSort")
    public Map<String,Object> deleteSecSort(@RequestParam(value = "sortName")String sortName,
                                            @RequestParam(value = "upperName")String upperName){
        System.out.println(sortName);
        if(sortService.deleteSort(upperName, sortName)>0){
            return ResultUtil.resultCode(200,"删除分类成功");
        }
        return ResultUtil.resultCode(500,"删除分类失败");
    }


}

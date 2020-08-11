package com.huang.store.controller;

import com.huang.store.entity.book.Publish;
import com.huang.store.service.imp.BookService;
import com.huang.store.service.imp.PublishService;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class PublishController {

    @Autowired
    @Qualifier("firstPublish")
    PublishService publishService;

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;


    /**
     * 添加出版社
     * @param publish
     * @return
     */
    @PostMapping("/admin/addPublish")
    public Map<String,Object> addPublish(@RequestBody Publish publish){
        Map<String,Object> map = new HashMap<>();
        System.out.println(publish.isShowPublish());
        System.out.println(publish.toString());
        if(publishService.addPublish(publish)>0){
            return ResultUtil.resultSuccess(map);
        }
        return ResultUtil.resultError(map);
    }

    /**
     *
     * @param page
     * @param pageSize
     * @return 按页得到出版社集合
     */
    @GetMapping("/getPublishList")
    public Map<String,Object> getPublishList(@RequestParam(value = "page")int page,
                                             @RequestParam(value = "pageSize")int pageSize){
        Map<String,Object> map = new HashMap<>();
        List<Publish> publishList = publishService.getPublishByPage(page,pageSize);
        int num;
        for(int i=0;i<publishList.size();i++){
            num = bookService.getPublishBookNum(publishList.get(i).getName());
            publishList.get(i).setNum(num);
        }
        int total = publishService.getPublishCount();
        map.put("total",total);
        map.put("publishList",publishList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到所有出版社的名字
     * @return
     */
    @GetMapping("/getPublishNames")
    public Map<String,Object> getPublishNames(){
        Map<String,Object> map = new HashMap<>();
        List<String> publishNames = publishService.getPublishNames();
        map.put("publishList",publishNames);
        return ResultUtil.resultSuccess(map);
    }


    //得到某个出版社的信息

    /**
     * 得到某个出版社的信息
     * @param id
     * @return
     */
    @GetMapping("/getEditPublish")
    Map<String,Object> getEditPublish(@RequestParam int id){
        Map<String,Object> map = new HashMap<>();
        Publish publish = publishService.getPublishById(id);
        map.put("publish",publish);
        return ResultUtil.resultSuccess(map);
    }

    //修改某个出版社信息

    /**
     * 修改某个出版社信息
     * @param publish
     * @return
     */
    @PostMapping("/modifyPublish")
    Map<String,Object> modifyPublish(@RequestBody Publish publish){
        Map<String,Object> map = new HashMap<>();
        if(publishService.modifyPublish(publish)>0){
            System.out.println("修改成功");
            return ResultUtil.resultSuccess(map);
        }
        return ResultUtil.resultError(map);
    }


    /**
     * 修改出版社是否显示
     * @param id
     * @return
     */
    @GetMapping("/modifyShowPublish")
    Map<String,Object> modifyIsShow(@RequestParam int id){
        if(publishService.modifyIsShow(id)>0){
            System.out.println("修改成功");
            return ResultUtil.resultCode(200,"修改成功！");
        }
        return ResultUtil.resultCode(500,"修改失败");
    }

    /**
     * 删除某个出版社
     * @param id
     * @return
     */
    @GetMapping("/delPublish")
    Map<String,Object> delPublish(@RequestParam int id){
        if(publishService.deletePublish(id)>0){
            System.out.println("删除成功");
            return ResultUtil.resultCode(200,"删除成功");
        }
        return ResultUtil.resultCode(500,"删除失败");
    }

}

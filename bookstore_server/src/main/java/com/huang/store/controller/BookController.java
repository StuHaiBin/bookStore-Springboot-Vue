package com.huang.store.controller;

import com.huang.store.entity.book.BookSort;
import com.huang.store.entity.book.Recommend;
import com.huang.store.entity.dto.SortBookRes;
import com.huang.store.service.imp.BookService;
import com.huang.store.service.imp.SortService;
import com.huang.store.util.FileUtil;
import com.huang.store.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

import com.huang.store.entity.book.Book;

@Controller
@ResponseBody
@RequestMapping(value = "/book")
public class BookController {

    private String basePath="D://ITsoftware//IDEA//data//Vue//book_01//";
    private String bookPath="static//image//book//";

    @Autowired
    @Qualifier("firstVersion")
    BookService bookService;

    @Autowired
    @Qualifier("firstSort")
    SortService sortService;

    /**
     * 添加图书
     * @param book
     * @return
     */
    @PostMapping("/addBook")
    public Map<String,Object> addBook(@RequestBody Book book){
        bookService.addBook(book);
        int bookId = bookService.getBookId(book.getisbn());
        System.out.println("bookId:"+bookId);
        boolean newProduct = book.isNewProduct();
        Recommend r = new Recommend();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        r.setAddTime(timestamp);
        r.setBookId(bookId);
        if(newProduct){
            bookService.addToNewProduct(r);
        }
        boolean recommend = book.isRecommend();
        if(recommend){
            bookService.addToRecommend(r);
        }
        int bookSort[] = book.getBookSort();
        if(bookSort.length==1){
            System.out.println(bookSort[0]);
            bookService.addBookToSort(bookSort[0],bookId);
        }else {
            System.out.println(bookSort[1]);
            bookService.addBookToSort(bookSort[1],bookId);
        }
        return ResultUtil.resultCode(200,"上传图书成功");
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    @PostMapping("/modifyBook")
    public Map<String,Object> modifyBook(@RequestBody Book book){
        System.out.println("修改图书起作用了");
        System.out.println(book.toString());
        int bookId = book.getId();
        String oldIsbn = bookService.getBookIsbn(bookId);
        bookService.modifyBookImgList(oldIsbn,book.getisbn());
        bookService.modifyBook(book);
        boolean newProduct = book.isNewProduct();
        Recommend r = new Recommend();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        r.setAddTime(timestamp);
        r.setBookId(bookId);
        int isExistInRec = bookService.hasExistInRec(bookId);
        int isExistInNew = bookService.hasExistInNew(bookId);
        if(isExistInNew==0&&newProduct){
            bookService.addToNewProduct(r);
        }
        if(isExistInNew==1&&!newProduct){
            bookService.deleteFromNewProduct(bookId);
        }
        boolean recommend = book.isRecommend();
        if(recommend&&isExistInRec==0){
            bookService.addToRecommend(r);
        }
        if(!recommend&&isExistInRec==1){
            bookService.deleteFromRecommend(bookId);
        }
        int bookSort[] = book.getBookSort();
        BookSort bookSort1 = bookService.getBookSort(bookId);
        if(bookSort.length==1&&bookSort1.getId()!=bookSort[0]){
            bookService.modifyBookSort(bookSort[0],bookId);
        }
        if(bookSort.length==2&&bookSort1.getId()!=bookSort[1]){
            bookService.modifyBookSort(bookSort[1],bookId);
        }
        return ResultUtil.resultCode(200,"修改图书成功");
    }

    /**
     * 修改图书下架
     * @param bookId
     * @param put
     * @return
     */
    @GetMapping("/modifyPut")
    public Map<String,Object> handlePut(@RequestParam(value = "bookId")int bookId,
                                        @RequestParam(value = "put")boolean put){
        System.out.println(bookId);
        System.out.println(put);
        bookService.modifyBookPut(bookId,put);
        return ResultUtil.resultCode(200,"修改成功");
    }

    /**
     * 修改指定图书是否为推荐状态
     * @param bookId
     * @param recommend
     * @return
     */
    @GetMapping("/modifyRec")
    public Map<String,Object> handleRec(@RequestParam(value = "bookId")int bookId,
                                        @RequestParam(value = "recommend")boolean recommend){
        int isExistInRec = bookService.hasExistInRec(bookId);
        if(isExistInRec==1&&!recommend){
            bookService.deleteFromRecommend(bookId);
        }
        if(isExistInRec==0&&recommend){
            Recommend r = new Recommend();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            r.setAddTime(timestamp);
            r.setBookId(bookId);
            bookService.addToRecommend(r);
        }
        bookService.modifyBookRec(bookId,recommend);
        return ResultUtil.resultCode(200,"修改成功");
    }

    /**
     * 修改图书是否为新品状态
     * @param bookId
     * @param newProduct
     * @return
     */
    @GetMapping("/modifyNew")
    public Map<String,Object> handleNew(@RequestParam(value = "bookId")int bookId,
                                        @RequestParam(value = "newProduct")boolean newProduct){
        int isExistInNew = bookService.hasExistInNew(bookId);
        System.out.println("isExistInNew:"+isExistInNew);
        System.out.println("newProduct:"+newProduct);
        if(isExistInNew==1&&!newProduct){
            System.out.println("删除新品推荐！");
            bookService.deleteFromNewProduct(bookId);
        }
        if(isExistInNew==0&&newProduct){
            Recommend r = new Recommend();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            r.setAddTime(timestamp);
            r.setBookId(bookId);
            bookService.addToNewProduct(r);
        }
        bookService.modifyBookNewPro(bookId,newProduct);
        return ResultUtil.resultCode(200,"修改成功");
    }

    /**
     * 按页得到图书的集合
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getBookList")
    public Map<String, Object> getBook(@RequestParam(value = "page")int page,
                                       @RequestParam(value = "pageSize")int pageSize){
        System.out.println("=========================按页得到图书的集合========================");
        Map<String, Object> map = new HashMap<>();
        List<Book> bookList = bookService.getBooksByPage(page, pageSize);
        for(int i=0;i<bookList.size();i++){
            String img = bookService.getBookCover(bookList.get(i).getisbn());
            System.out.println("=======设置了图书的封面图片========");
            bookList.get(i).setCoverImg(img);
        }
        map.put("bookList",bookList);
        int total = bookService.getBookCount();
        map.put("total",total);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到某个分类的图书集合
     * @param sortId
     * @return
     */
    @GetMapping(value = "/getSortBookList")
    public Map<String, Object> getSortBookList(@RequestParam(value = "sortId")int sortId){
        BookSort bookSort = sortService.getBookSortById(sortId);
        List<Book> upperBookList = bookService.getBooksByFirst(bookSort.getSortName(),1,14);
        for(int i=0;i<upperBookList.size();i++){
            String img = bookService.getBookCover(upperBookList.get(i).getisbn());
            upperBookList.get(i).setCoverImg(img);
        }
        SortBookRes bookRes = new SortBookRes();
        bookRes.setBookList(upperBookList);
        bookRes.setSortId(bookSort.getId());
        bookRes.setSortName(bookSort.getSortName());
        List<SortBookRes> sortBookResList = new ArrayList<>();
        sortBookResList.add(bookRes);
        List<BookSort> bookSortList = sortService.getSecondSorts(bookSort.getSortName(),1,6);
        for(int i=0;i<bookSortList.size();i++){
            List<Book> bookList = bookService.getBookBySecond(bookSortList.get(i).getId(),1,14);
            for(int j=0;j<bookList.size();j++){
                String img = bookService.getBookCover(bookList.get(j).getisbn());
                bookList.get(j).setCoverImg(img);
            }
            SortBookRes bookRes1 = new SortBookRes();
            bookRes1.setSortId(bookSortList.get(i).getId());
            bookRes1.setSortName(bookSortList.get(i).getSortName());
            bookRes1.setBookList(bookList);
            sortBookResList.add(bookRes1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sortBookResList",sortBookResList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 按页得到某一个分类的图书集合（需要先判定前端传过的是一级分类还是二级分类）
     * @param sortId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getBookListBySort")
    public Map<String, Object> getSortBookListBySort(@RequestParam(value = "sortId")int sortId,
                                                     @RequestParam(value = "page")int page,
                                                     @RequestParam(value = "pageSize")int pageSize){
        BookSort bookSort = sortService.getBookSortById(sortId);
        int total = 0;
        List<Book> bookList = new ArrayList<>();
        if(bookSort.getUpperName().equals("无")){
            //说明是一级分类
            bookList = bookService.getBooksByFirst(bookSort.getSortName(),page,pageSize);
            total = bookService.getFirstBookCount(bookSort.getSortName());
        }else {
            //说明这是二级分类
            bookList = bookService.getBookBySecond(bookSort.getId(),page,pageSize);
            total = bookService.getSecondBookCount(bookSort.getId());
        }
        for(int i=0;i<bookList.size();i++){
            String img = bookService.getBookCover(bookList.get(i).getisbn());
            bookList.get(i).setCoverImg(img);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("bookList",bookList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到首页推荐的图书集合
     * @param sort
     * @return
     */
    @GetMapping(value = "/getRecBookList")
    public Map<String, Object> getRecBook(@RequestParam(value = "sort")String sort){
        Map<String, Object> map = new HashMap<>();
        List<Book> bookList = new ArrayList<>();
        switch (sort){
            case "recommend":
                bookList = bookService.getRecommendsByPage(1, 14);
                break;
            case "newProduct":
                bookList = bookService.getNewProductsByPage(1, 14);
                break;
            case "newPut":
                bookList = bookService.getNewPutBookList(1, 10);
                break;
            default:
                return ResultUtil.resultCode(200,"批量操作失败");
        }
                bookService.getNewProductsByPage(1, 14);
        for(int i=0;i<bookList.size();i++){
            String img = bookService.getBookCover(bookList.get(i).getisbn());
            bookList.get(i).setCoverImg(img);
        }
        map.put("bookList",bookList);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 按id得到某本图书的集合
     * @param id
     * @return
     */
    @GetMapping("/getBook")
    Map<String,Object> getBook(@RequestParam int id){
        System.out.println("==========查询某本图书的数据集合==============");
        Map<String,Object> map = new HashMap<>();
        System.out.println("id:"+id);
        Book book = bookService.getBook(id);
        List<String> img = bookService.getBookImgSrcList(book.getisbn());
        book.setImgSrc(img);
        System.out.println("=======图书的封面：========="+book.getImgSrc()+"=========");
        BookSort bookSort = bookService.getBookSort(id);
        int upperId=0;
        int childId=0;
        if(!bookSort.getUpperName().equals("无")){
            upperId = sortService.getBookSortId("无",bookSort.getUpperName());
            map.put("upperId",upperId);
            childId = bookSort.getId();
            map.put("childId",childId);
        }
        if(bookSort.getUpperName().equals("无")){
            childId = bookSort.getId();
            map.put("upperId",childId);
        }
//        System.out.println("获取出来的bookSort:"+bookSort);
        map.put("book",book);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 得到某本图书的图书相册集合
     * @param isbn
     * @return
     */
    @GetMapping("/getImgPaths")
    public Map<String,Object> getBookImgPaths(@RequestParam(value = "bookId")String isbn){
        Map<String,Object> map = new HashMap<>();
        List<String> imgPaths = bookService.getBookImgSrcList(isbn);
        map.put("imgPaths",imgPaths);
        return ResultUtil.resultSuccess(map);
    }

    /**
     * 删除某本图书
     * @param bookId
     * @return
     */
    @GetMapping("/delBook")
    public Map<String,Object> delBook(@RequestParam(value = "bookId")int bookId){
        System.out.println("删除图书起作用");
        System.out.println("isbn:"+bookId);
        Book book = bookService.getBook(bookId);
        List<String> imgPaths = bookService.getBookImgSrcList(book.getisbn());
        for(int i=0;i<imgPaths.size();i++){
            String path=basePath+imgPaths.get(i);
            imgPaths.set(i,path);
        }
        if(bookService.deleteBook(bookId)>0 && bookService.deleteBookImgOfOne(book.getisbn())>0){
            System.out.println("删除图片大于0");

            for(int i=0;i<imgPaths.size();i++){
                System.out.println(imgPaths.get(i));
            }
            FileUtil.delImg(imgPaths);
            return ResultUtil.resultCode(200,"删除图书成功");
        }
        return ResultUtil.resultCode(500,"删除图书失败");
    }

    /**
     * 批量处理图书
     * @param ids
     * @param operator
     * @return
     */
    @PostMapping("/batchDel")
    public Map<String,Object> batchBook(@RequestParam(value = "ids") int[] ids,
                                        @RequestParam(value = "operator")String operator){
        System.out.println("说明已经请求到了");
        System.out.println(Arrays.toString(ids));
        System.out.println(operator);
        switch (operator){
            case "del":
                bookService.batchDelBook(ids);
                break;
            case "put":
                bookService.batchPutBook(ids,true);
                break;
            case "putOff":
                bookService.batchPutBook(ids,false);
                break;
            case "recommend":
                bookService.batchRecBook(ids,true);
                break;
            case "recommendOff":
                bookService.batchRecBook(ids,false);
                break;
            case "newProduct":
                bookService.batchNewProBook(ids,true);
                break;
            case "newProductOff":
                bookService.batchNewProBook(ids,false);
                break;
            default:
                return ResultUtil.resultCode(200,"批量操作失败");
        }
        return ResultUtil.resultCode(200,"批量操作成功");
    }

}

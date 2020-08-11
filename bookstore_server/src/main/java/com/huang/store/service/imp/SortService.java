package com.huang.store.service.imp;

import com.huang.store.entity.book.BookSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SortService {
    int addSort(BookSort bookSort);
    int deleteSort(String upperName,String sortName);
    int deleteFirSort(String sortName);
    int modifySort(BookSort bookSort);
    int modifySortUpperName(String oldUpperName,String newUpperName);
    BookSort getBookSort(String upperName,String sortName);
    int getBookSortId(String upperName,String sortName);
    BookSort getBookSortById(int id);
    List<BookSort> getFirstSorts(int page,int pageSize);
    List<BookSort> getSecondSorts(String upperName,int page,int pageSize);//按页得到二级分类
    List<BookSort> getSecondSortList(String upperName);//得到对应父级父类下的所有子分类
    List<BookSort> getAllFirSorts();//得到所有的一级分类
    List<String> getUpperSorts();//得到所有一级分类的名称
    List<Integer> getAllFirSortId(String sortName);//得到某一级分类的所有子类及其自己的id号集
    List<Integer> getSecSortIdPage(String sortName,int page,int pageSize);//按页得到某一级分类的所有子类id号集

    int getFirstCount();
    int getSecondCount(String upperName);
}

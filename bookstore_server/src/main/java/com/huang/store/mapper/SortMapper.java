package com.huang.store.mapper;

import com.huang.store.entity.book.BookSort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortMapper {

    int addSort(BookSort bookSort);
    int deleteSort(String upperName,String sortName);
    int deleteFirSort(String sortName);
    int modifySort(BookSort bookSort);//修改图书分类
    int modifySortUpperName(String oldUpperName,String newUpperName);//修改上一级的分类名称后，需要修改下一级别的所有分类的的父级
    BookSort getBookSort(String upperName,String sortName);//通过分类的一级分类名和二级分类名查找分类
    int getBookSortId(String upperName,String sortName);//通过分类的一级分类名和二级分类名查找分类的id
    BookSort getBookSortById(int id);//通过分类的id号查询id
    List<BookSort> getFirstSorts(int page,int pageSize);//按页得到一级分类
    List<BookSort> getAllFirSorts();//得到所有的一级分类
    List<BookSort> getSecondSorts(String upperName,int page,int pageSize);//按页得到二级分类
    List<BookSort> getSecondSortList(String upperName);//得到对应父级父类下的所有子分类
    List<String> getUpperSorts();//得到一级分类的所有分类名
    List<Integer> getAllFirSortId(String sortName);//得到某一级分类的所有子类及其自己的id号集
    List<Integer> getSecSortIdPage(String sortName,int page,int pageSize);//按页得到某一级分类的所有子类id号集
    int getFirstCount();//得到一级分类的数量
    int getSecondCount(String upperName);//得到二级分类的数量



}

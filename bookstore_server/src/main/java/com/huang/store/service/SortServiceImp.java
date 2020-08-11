package com.huang.store.service;

import com.huang.store.entity.book.BookSort;
import com.huang.store.mapper.SortMapper;
import com.huang.store.service.imp.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("firstSort")
public class SortServiceImp implements SortService {

    @Autowired
    SortMapper sortMapper;

    @Override
    public int addSort(BookSort bookSort) {
        int result = sortMapper.addSort(bookSort);
        return result;
    }

    @Override
    public int deleteSort(String upperName, String sortName) {
        int result = sortMapper.deleteSort(upperName,sortName);
        return result;
    }

    @Override
    public int deleteFirSort(String sortName) {
        int result = sortMapper.deleteFirSort(sortName);
        return result;
    }

    @Override
    public int modifySort(BookSort bookSort) {
        int result = sortMapper.modifySort(bookSort);
        return result;
    }

    @Override
    public int modifySortUpperName(String oldUpperName, String newUpperName) {
        int result = sortMapper.modifySortUpperName(oldUpperName, newUpperName);
        return result;
    }

    @Override
    public BookSort getBookSort(String upperName, String sortName) {
        return sortMapper.getBookSort(upperName,sortName);
    }

    @Override
    public int getBookSortId(String upperName, String sortName) {
        return sortMapper.getBookSortId(upperName, sortName);
    }

    @Override
    public BookSort getBookSortById(int id) {
        return sortMapper.getBookSortById(id);
    }

    @Override
    public List<BookSort> getFirstSorts(int page, int pageSize) {
        int start=(page-1)*pageSize;
        return sortMapper.getFirstSorts(start,pageSize);
    }

    @Override
    public List<BookSort> getAllFirSorts() {
        return sortMapper.getAllFirSorts();
    }

    @Override
    public List<BookSort> getSecondSorts(String upperName, int page, int pageSize) {
        int start=(page-1)*pageSize;
        return sortMapper.getSecondSorts(upperName,start,pageSize);
    }

    @Override
    public List<BookSort> getSecondSortList(String upperName) {
        return sortMapper.getSecondSortList(upperName);
    }

    @Override
    public List<String> getUpperSorts() {
        return sortMapper.getUpperSorts();
    }

    @Override
    public List<Integer> getAllFirSortId(String sortName) {
        return sortMapper.getAllFirSortId(sortName);
    }

    @Override
    public List<Integer> getSecSortIdPage(String sortName, int page, int pageSize) {
        int start=(page-1)*pageSize;
        return sortMapper.getSecSortIdPage(sortName, start, pageSize);
    }

    @Override
    public int getFirstCount() {
        return sortMapper.getFirstCount();
    }

    @Override
    public int getSecondCount(String upperName) {
        return sortMapper.getSecondCount(upperName);
    }
}

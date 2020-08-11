package com.huang.store.entity.book;

/**
 * @author: 黄龙
 * @date: 2020/6/17 10:28
 * @description: 图书和分类关联类
 */
public class BookSortList {
    private int bookSortId;
    private int bookId;

    public int getBookSortId() {
        return bookSortId;
    }

    public void setBookSortId(int bookSortId) {
        this.bookSortId = bookSortId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "BookSortList{" +
                "bookSortId=" + bookSortId +
                ", bookId=" + bookId +
                '}';
    }
}

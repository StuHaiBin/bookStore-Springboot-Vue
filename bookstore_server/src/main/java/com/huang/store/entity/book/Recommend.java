package com.huang.store.entity.book;

import java.sql.Timestamp;

/**
 * @author: 黄龙
 * @date: 2020/7/22 21:17
 * @description: 推荐类
 */

public class Recommend {

    private int id;//编号
    private int bookId;//图书的编号
    private int rank;//推荐值
    private Timestamp addTime;//添加到推荐的时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", rank=" + rank +
                ", addTime=" + addTime +
                '}';
    }
}

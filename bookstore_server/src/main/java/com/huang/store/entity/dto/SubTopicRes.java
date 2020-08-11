package com.huang.store.entity.dto;

/**
 * @author: 黄龙
 * @date: 2020/7/20 15:50
 * @description: 子书单明细
 */
public class SubTopicRes {
    private int id;
    private String bookName;
    private String isbn;
    private String author;
    private String recommendReason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    @Override
    public String toString() {
        return "SubTopicRes{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", recommendReason='" + recommendReason + '\'' +
                '}';
    }
}

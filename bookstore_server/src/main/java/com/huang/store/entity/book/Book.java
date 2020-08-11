package com.huang.store.entity.book;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 黄龙
 * @date: 2020/7/22 21:44
 * @description: 图书实体类
 */
public class Book {
    private int id;
    private String bookName;
    private String author;
    private String isbn;
    private String publish;//出版社
    private Timestamp birthday;//出版时间
    private double marketPrice;//书的原价
    private double price;//书的售价
    private Integer stock;//库存
    private String description;//书的概述，在详情页展示
    private List<String> ImgSrc;//书的图片地址
    private boolean put;//是否上架
    private String coverImg;//书的封面图
    private int rank;//权重值
    private boolean newProduct;
    private boolean recommend;
    private int bookSort[];

    public Book() {
    }


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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getisbn() {
        return isbn;
    }

    public void setisbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<String> getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(List<String> imgSrc) {
        ImgSrc = imgSrc;
    }

    public boolean isPut() {
        return put;
    }

    public void setPut(boolean put) {
        this.put = put;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public int[] getBookSort() {
        return bookSort;
    }

    public void setBookSort(int[] bookSort) {
        this.bookSort = bookSort;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publish='" + publish + '\'' +
                ", birthday=" + birthday +
                ", marketPrice=" + marketPrice +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", ImgSrc=" + ImgSrc +
                ", put=" + put +
                ", coverImg='" + coverImg + '\'' +
                ", rank=" + rank +
                ", newProduct=" + newProduct +
                ", recommend=" + recommend +
                ", bookSort=" + Arrays.toString(bookSort) +
                '}';
    }
}

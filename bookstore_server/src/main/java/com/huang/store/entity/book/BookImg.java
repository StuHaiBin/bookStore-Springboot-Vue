package com.huang.store.entity.book;

/**
 * @author: 黄龙
 * @date: 2020/6/16 16:31
 * @description: 图书图片类
 */
public class BookImg {
    private int id;
    private String isbn;//书的ISBN
    private String imgSrc;
    private boolean cover;//是否封面显示


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean isCover() {
        return cover;
    }

    public void setCover(boolean cover) {
        this.cover = cover;
    }
}

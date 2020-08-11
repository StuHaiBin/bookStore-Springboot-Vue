package com.huang.store.entity.book;

/**
 * @author: 黄龙
 * @date: 2020/6/10 11:26
 * @description: 图书分类
 */
public class BookSort {
    private int id;
    private String sortName;
    private String upperName;//上一级的标签名
    private String level;
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "BookSort{" +
                "id=" + id +
                ", sortName='" + sortName + '\'' +
                ", upperName='" + upperName + '\'' +
                ", level='" + level + '\'' +
                ", rank=" + rank +
                '}';
    }
}

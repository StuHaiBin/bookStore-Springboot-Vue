package com.huang.store.entity.book;

import java.util.List;

/**
 * @author: 黄龙
 * @date: 2020/6/18 16:05
 * @description: 书单专题
 */
public class BookTopic {
    private int id;//书单编号
    private String topicName;//书单专题名
    private String subTitle;//副标题
    private String cover;//书单封面
    private int rank;
    private boolean put;
    private List<SubBookTopic> subBookTopics;//书单包含的图书

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<SubBookTopic> getSubBookTopics() {
        return subBookTopics;
    }

    public void setSubBookTopics(List<SubBookTopic> subBookTopics) {
        this.subBookTopics = subBookTopics;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isPut() {
        return put;
    }

    public void setPut(boolean put) {
        this.put = put;
    }

    @Override
    public String toString() {
        return "BookTopic{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", rank=" + rank +
                ", put=" + put +
                ", subBookTopics=" + subBookTopics +
                '}';
    }
}

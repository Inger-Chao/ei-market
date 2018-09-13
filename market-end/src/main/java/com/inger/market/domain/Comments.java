package com.inger.market.domain;/**
 * Created by IngerJo on 2018/9/13.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-13 15:53
 **/
@Entity
public class Comments {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private String comments;

    private String createTime;

    public Comments() {}

    public Comments(Integer userId, Integer goodsId, String comments, String createTime) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.comments = comments;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}

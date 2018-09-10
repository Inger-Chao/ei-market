package com.inger.market.domain;/**
 * Created by IngerJo on 2018/9/10.
 */

import javax.persistence.*;

/**
 * @program: market
 * @description: 自定义 Cart 实体，某用户的购物车数据
 * @author: IngerJo
 * @create: 2018-09-10 11:11
 **/



@Entity
public class Cart {

    @Id
    @GeneratedValue()
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    public Cart(Integer id, Integer userId, Integer goodsId) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public Cart() {

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
}

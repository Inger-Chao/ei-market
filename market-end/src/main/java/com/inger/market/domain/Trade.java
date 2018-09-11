package com.inger.market.domain;/**
 * Created by IngerJo on 2018/9/10.
 */

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-10 18:32
 **/
@Entity
public class Trade {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer salerId;

    private Integer buyerId;

    private Integer goodsId;

    private Integer status = 2;

    public Trade() { }

    public Trade(Integer salerId, Integer buyerId, Integer goodsId, Integer status) {
        this.salerId = salerId;
        this.buyerId = buyerId;
        this.goodsId = goodsId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalerId() {
        return salerId;
    }

    public void setSalerId(Integer salerId) {
        this.salerId = salerId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

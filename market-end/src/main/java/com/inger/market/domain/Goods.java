package com.inger.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@NamedNativeQuery(
        name = "Goods.findByUserIdInCart",
        query = "SELECT cart.user_id,cart.goods_id,goods.name,goods.price,goods.img_url,goods.status FROM cart,goods " +
                "where cart.goods_id = goods.id " +
                "and cart.user_id = :uid",
        resultClass = Cart.class
)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "name")
    private String name; //商品名

    @Column(name = "price")
    private float price; //价格

    @Column(name = "real_price")
    private float realPrice; //原价

    @Column(name = "create_time", updatable = false)
    private String createTime;

    @Column(name = "end_time", columnDefinition = "Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endTime;

    @Column(name = "polish_time", columnDefinition = "Date")
    private String polishTime;

    @Column(name = "comment_num", columnDefinition = "Date")
    private Integer commentNum;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    //上架 or 下架
    private Integer status = 1;

    public Goods() {
    }

    public Goods(String name, float price, String createTime,String imgUrl) {
        this.name = name;
        this.price = price;
        this.createTime = createTime;
        this.imgUrl = imgUrl;
    }

    public Goods(String name, float price, String imgUrl, Integer status) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public Goods(Integer userId, Integer typeId, String name, float price, float realPrice, String createTime, String endTime, String polishTime, Integer commentNum, String description, String imgUrl, Integer status) {
        this.userId = userId;
        this.typeId = typeId;
        this.name = name;
        this.price = price;
        this.realPrice = realPrice;
        this.createTime = createTime;
        this.endTime = endTime;
        this.polishTime = polishTime;
        this.commentNum = commentNum;
        this.description = description;
        this.imgUrl = imgUrl;
        this.status = status;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getrealPrice() {
        return realPrice;
    }

    public void setrealPrice(float realPrice) {
        this.realPrice = realPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPolishTime() {
        return polishTime;
    }

    public void setPolishTime(String polishTime) {
        this.polishTime = polishTime;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

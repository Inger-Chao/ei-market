package com.inger.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Goods {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type_id",columnDefinition = "Integer")
    private Type type;

    @Column(name = "user_id")
    private Integer userId;

/*    @Column(name = "type_id")
    private Integer typeId;*/

    @Column(name = "name")
    private String name; //商品名

    @Column(name = "price")
    private float price; //价格

    @JsonIgnore
    @Column(name = "real_price")
    private float realPrice; //原价

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "polish_time")
    private String polishTime;

    @Column(name = "comment_num")
    private Integer commentNum;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    public Goods() {
    }

    public Goods(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Goods(Type type, Integer userId, String name, float price, float realPrice, String createTime, String endTime, String polishTime, Integer commentNum, String description) {
        this.type = type;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.realPrice = realPrice;
        this.createTime = createTime;
        this.endTime = endTime;
        this.polishTime = polishTime;
        this.commentNum = commentNum;
        this.description = description;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
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
}

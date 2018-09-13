package com.inger.market.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by IngerJo on 2018/9/5.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false,updatable = false)
    private String username;

    @Length(min = 6, max = 16,message = "password length must between 6 and 16")
    private String password;

    private String phone;

    private String qq;

    @Column(name = "create_time",updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @Column(name = "goods_num")
    private Integer goodsNum = 0;

    @Column(name = "power",updatable = false)
    private Integer power;

    private float coin;

    private float credit = 5;

    public User() {
    }

    public User(String username, @Length(min = 6, max = 16, message = "password length must between 6 and 16") String password, String phone, String qq, Date createTime, Integer goodsNum, Integer power, float coin, float credit) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.qq = qq;
        this.createTime = createTime;
        this.goodsNum = goodsNum;
        this.power = power;
        this.coin = coin;
        this.credit = credit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public float getCoin() {
        return coin;
    }

    public void setCoin(float coin) {
        this.coin = coin;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }
}

package com.inger.market.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inger.market.utils.TypeSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@SqlResultSetMapping(
        name = "GoodsSimpleInfo",
        classes = {
                @ConstructorResult(
                        targetClass = Goods.class,
                        columns = {
                                @ColumnResult(name = "goods.name", type = String.class),
                                @ColumnResult(name = "goods.price", type = Float.class),
                                @ColumnResult(name = "goods.create_time", type = String.class),
                                @ColumnResult(name = "goods.img_url", type = String.class),
                        }
                )
        }
)
@NamedNativeQuery(
        name = "Type.findGoods",
        query = "SELECT goods.name,goods.price,goods.create_time,goods.img_url " +
                "from  goods,type " +
                "WHERE type_id = type.id and type.name like concat('%',:typeName,'%') ",
        resultSetMapping = "GoodsSimpleInfo"
)
@JsonSerialize(using = TypeSerialize.class)
public class Type {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    private Integer number;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }



}

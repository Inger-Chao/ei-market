package com.inger.market.controller;

import com.inger.market.ResultEnum;
import com.inger.market.domain.Type;
import com.inger.market.domain.Goods;
import com.inger.market.domain.Result;
import com.inger.market.repository.TypeRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TypeController {

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/goods/{type}/{page}")
    public Result<List<Goods>> getGoodsListByType(@PathVariable("type") String type,
                                                  @PathVariable("page") int page) {
        List<Goods> goods = typeRepository.findGoods(type, PageRequest.of(page,12));
        System.out.println(type);
        System.out.println("goods.size = " + goods.size());
        if (goods.isEmpty()){
            System.out.println("empty");
        }else{
            for (Goods i: goods
                 ) {
                System.out.println(i.getName());
            }
        }
        return ResultUtil.success(ResultEnum.GET_GOODS_TYPE_LIST, goods);
    }


    @GetMapping(value = "/types")
    public Result<List<Type>> getAllTypes(){
        return ResultUtil.success(ResultEnum.GET_TYPES, typeRepository.findAll());
    }


}

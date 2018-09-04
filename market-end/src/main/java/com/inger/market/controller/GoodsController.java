package com.inger.market.controller;

import com.inger.market.ResultEnum;
import com.inger.market.domain.Goods;
import com.inger.market.domain.Result;
import com.inger.market.repository.GoodsRepository;
import com.inger.market.repository.TypeRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IngerJo on 2018/9/1.
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping(value = "/goods-list/{page}")
    public Result<List<Goods>> getAllGoodsList(@PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page,12);
        Page<Goods> goods = goodsRepository.findAll(pageable);
        return ResultUtil.success(ResultEnum.GET_GOODS_INFO_LIST, goods.getContent());
    }

//    通过TypeId在Goods表中查询当前分类下的商品数量
    @GetMapping(value = "/count/{type}")
    public Result<Integer> getGoodsCountByType(@PathVariable("type") String type){
        Integer typeId = typeRepository.findByName(type).getId();
        return ResultUtil.success(ResultEnum.GET_GOODS_COUNT,
                goodsRepository.countByTypeId(typeId));
    }

}

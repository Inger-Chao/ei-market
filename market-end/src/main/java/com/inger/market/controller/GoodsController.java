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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "goods/content/{goodsId}")
    public Result<Goods> getOneGoodsById(@PathVariable("goodsId") Integer goodsId){

        Goods goods = goodsRepository.findById(goodsId).get();

        return ResultUtil.success(ResultEnum.GET_GOODS_DETAIL,goods);
    }

    @GetMapping(value = "/search/{key}/{page}")
    public Result<List<Goods>> searchGoodsByKey(@PathVariable("key") String key,
                                                @PathVariable("page") int page){

        Pageable pageable = PageRequest.of(page, 12);
        Page<Goods> goods =  goodsRepository.findByNameLikeAndAndDescription(key,pageable);
        return ResultUtil.success(ResultEnum.GET_GOODS_BY_KEY,goods.getContent());
    }

    /* 通过用户 ID 获取用户发布的所有闲置商品 */
    @GetMapping(value = "/goods/user/{userId}")
    public Result<List<Goods>> getGoodsListByUserId(@PathVariable("userId") Integer userId){
        List<Goods> goods = goodsRepository.findByUserId(userId);
        return ResultUtil.success(ResultEnum.GET_USER_GOODS_LIST,goods);
    }

    @PostMapping(value = "/goods/change/status/{goodsId}")
    public Result changeGoodsStatus(@PathVariable("goodsId") Integer goodsId){
        Goods goods = goodsRepository.findById(goodsId).get();
        goods.setStatus((goods.getStatus() == 0)? 1 : 0);
        goodsRepository.save(goods);
        return ResultUtil.success(ResultEnum.UPDATE_GOODS);
    }

    @PostMapping(value = "/goods/add")
    public Result addGoods(@Valid Goods goods){
        goodsRepository.saveAndFlush(goods);
        return ResultUtil.success(ResultEnum.ADD_GOODS);
}


    @Transactional
    @PostMapping(value = "/goods/delete/{id}")
    public Result deleteGoodsById(@PathVariable("id") Integer id){
        goodsRepository.deleteById(id);
        return ResultUtil.success(ResultEnum.DELETE_GOODS);
    }

    @Transactional
    @PostMapping(value = "/goods/delete/more")
    public Result deleteMoreGoods(@RequestParam("goods") String selectGoods){
        for ( String goodsId : selectGoods.split("&")) {
            Result result = deleteGoodsById(Integer.parseInt(goodsId));
            if (!result.getStatus().equals(ResultEnum.DELETE_GOODS.getStatus())) {
                return result;
            }
        }
        return ResultUtil.success(ResultEnum.DELETE_MORE_GOODS);
    }

}

package com.inger.market.controller;/**
 * Created by IngerJo on 2018/9/10.
 */

import com.inger.market.ResultEnum;
import com.inger.market.domain.Cart;
import com.inger.market.domain.Goods;
import com.inger.market.domain.Result;
import com.inger.market.repository.CartRepository;
import com.inger.market.repository.GoodsRepository;
import com.inger.market.repository.UserRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-10 13:40
 **/
@RestController
public class CartController {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private CartRepository cartRepository;


    @GetMapping(value = "/cart-list/{userId}")
    public Result<List<Goods>> getOnesCartList(@PathVariable("userId") Integer userId){

        List<Cart> carts = cartRepository.findByUserId(userId);
        List<Goods> goods = new ArrayList<Goods>();
        for ( Cart i : carts
             ) {
            goods.add(goodsRepository.findById(i.getGoodsId()).get());
        }
        return ResultUtil.success(ResultEnum.GET_ONES_CART,goods);
    }

    @PostMapping(value = "/cart/add/{userId}/{goodsId}")
    public Result addCart(@PathVariable("userId") Integer userId,
                          @PathVariable("goodsId") Integer goodsId){
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setGoodsId(goodsId);
        cartRepository.saveAndFlush(cart);
        return ResultUtil.success(ResultEnum.ADD_CART);
    }

    @Transactional
    @PostMapping(value = "/cart/delete/{userId}/{goodsId}")
    public Result deleteCart(@PathVariable("userId") Integer userId,
                             @PathVariable("goodsId") Integer goodsId){

        cartRepository.deleteByUserIdAndGoodsId(userId,goodsId);
        return ResultUtil.success(ResultEnum.DROP_CART);
    }

}

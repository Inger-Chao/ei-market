package com.inger.market.controller;/**
 * Created by IngerJo on 2018/9/10.
 */

import com.inger.market.ResultEnum;
import com.inger.market.domain.Goods;
import com.inger.market.domain.Result;
import com.inger.market.domain.Trade;
import com.inger.market.repository.GoodsRepository;
import com.inger.market.repository.TradeRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-10 18:41
 **/

@RestController
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping(value = "/trade-list/{BuyerId}")
    public Result<List<Goods>> getGoodsByBuyerId(@PathVariable("BuyerId") Integer buyerId){

        List<Trade> trades = tradeRepository.findByBuyerId(buyerId);

        List<Goods> goods = new ArrayList<Goods>();

        for (Trade i  : trades
             ) {
            goods.add(goodsRepository.findById(i.getGoodsId()).get());
        }
        return ResultUtil.success(ResultEnum.GET_ONES_BOUGHT_GOODS,goods);
    }

    @GetMapping(value = "trade/info/{goodsId}")
    public Result<Trade> getGoodsTradeInfo(@PathVariable("goodsId") Integer goodsId){
        return ResultUtil.success(ResultEnum.GET_TRADE_INFO,tradeRepository.findByGoodsId(goodsId));
    }

    @GetMapping(value = "/trade/status/{buyerId}/{status}")
    public Result<List<Goods>> getGoodsListByBuyerIdAndStatus(@PathVariable("buyerId") Integer buyerId,
                                                              @PathVariable("status") Integer status){

        List<Trade> trades = tradeRepository.findByBuyerIdAndStatus(buyerId,status);
        List<Goods> goods = new ArrayList<Goods>();

        for (Trade i : trades
             ) {
            goods.add(goodsRepository.findById(i.getGoodsId()).get());
        }
        return ResultUtil.success(ResultEnum.GET_ONES_BOUGHT_GOODS,goods);
    }

    @PostMapping(value = "/trade/add/{salerId}/{buyerId}/{goodsId}")
    public Result addTrade(@PathVariable("salerId") Integer salerId,
                           @PathVariable("buyerId") Integer buyerId,
                           @PathVariable("goodsId") Integer goodsId){
        Trade trade = new Trade();
        trade.setSalerId(salerId);
        trade.setBuyerId(buyerId);
        trade.setGoodsId(goodsId);
        tradeRepository.save(trade);
        return ResultUtil.success(ResultEnum.ADD_TRADE);
    }

    @PostMapping(value = "/trade/change/{goodsId}/{status}")
    public Result changeTradeStatus(@PathVariable("goodsId") Integer goodsId,
                                    @PathVariable("status") Integer status){

        Trade trade = tradeRepository.findByGoodsId(goodsId);

        if (trade == null){
            return ResultUtil.error(ResultEnum.TRADE_NOT_FOUND);
        }

        trade.setStatus(status);
        tradeRepository.save(trade);

        return ResultUtil.success(ResultEnum.CHANGE_TRADE_STATUS);
    }

    @Transactional
    @PostMapping(value = "/trade/delete/{goodsId}")
    public Result deleteTradeByGoodsId(@PathVariable("goodsId") Integer goodsId){
        tradeRepository.deleteByGoodsId(goodsId);
        return ResultUtil.success(ResultEnum.DROP_TRADE);
    }

}

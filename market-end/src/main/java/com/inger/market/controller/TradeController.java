package com.inger.market.controller;/**
 * Created by IngerJo on 2018/9/10.
 */

import com.inger.market.domain.Goods;
import com.inger.market.domain.Result;
import com.inger.market.domain.Trade;
import com.inger.market.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-10 18:41
 **/

@RestController
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;



}

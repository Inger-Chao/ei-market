package com.inger.market.repository;

import com.inger.market.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IngerJo on 2018/9/10.
 */
public interface TradeRepository extends JpaRepository<Trade,Integer> {

    List<Trade> findByBuyerId(Integer buyerId);

    List<Trade> findByBuyerIdAndStatus(Integer buyerId, Integer status);

    Trade findByGoodsId(Integer goodsId);

    void deleteByGoodsId(Integer goodsId);
}

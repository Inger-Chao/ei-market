package com.inger.market.repository;

import com.inger.market.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IngerJo on 2018/9/10.
 */
public interface TradeRepository extends JpaRepository<Trade,Integer> {
}

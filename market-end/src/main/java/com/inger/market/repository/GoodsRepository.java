package com.inger.market.repository;

import com.inger.market.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IngerJo on 2018/9/1.
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    Integer countByTypeId(Integer typeId);
}

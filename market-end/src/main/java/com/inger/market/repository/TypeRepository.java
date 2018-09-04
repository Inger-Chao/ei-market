package com.inger.market.repository;

import com.inger.market.domain.Type;
import com.inger.market.domain.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type findByName(String name);

    @Query(nativeQuery = true)
    List<Goods> findGoods(@Param("typeName") String Type, Pageable pageable);

}
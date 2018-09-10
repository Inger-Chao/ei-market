package com.inger.market.repository;

import com.inger.market.domain.Goods;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

import java.util.List;
import java.util.Optional;

/**
 * Created by IngerJo on 2018/9/1.
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
    Integer countByTypeId(Integer typeId);

    //本地模糊查询：通过商品名字或描述中的关键字查找
    @Query(value = "select * from goods " +
            "where goods.name like concat('%',:key,'%') " +
            "or goods.description like concat('%',:key,'%') ",nativeQuery = true)
    Page<Goods> findByNameLikeAndAndDescription(@Param("key") String key, Pageable pageable);

    List<Goods> findByUserId(Integer userId);

    Goods findByName(String name);

    @NotNull
    Optional<Goods> findById(Integer id);

    void deleteByName(String name);
}

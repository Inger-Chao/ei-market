package com.inger.market.repository;

import com.inger.market.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IngerJo on 2018/9/13.
 */
public interface CommentsRepository extends JpaRepository<Comments,Integer>{

    List<Comments> findByGoodsId(Integer goodsId);

}


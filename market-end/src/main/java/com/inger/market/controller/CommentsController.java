package com.inger.market.controller;/**
 * Created by IngerJo on 2018/9/13.
 */

import com.inger.market.ResultEnum;
import com.inger.market.domain.Comments;
import com.inger.market.domain.Result;
import com.inger.market.repository.CommentsRepository;
import com.inger.market.repository.UserRepository;
import com.inger.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: market
 * @author: IngerJo
 * @create: 2018-09-13 15:57
 **/
@RestController
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/comments/{goodsId}")
    public Result<List<Comments>> getGoodsComments(@PathVariable("goodsId") Integer goodsId){

        List<Comments> comments =  commentsRepository.findByGoodsId(goodsId);

        return ResultUtil.success(ResultEnum.GET_GOODS_COMMENTS_LIST,comments);
    }

    @PostMapping(value = "/comments/add/{goodsId}/{userId}/{comment}")
    public Result addGoodsComments(@PathVariable("goodsId") Integer goodsId,
                                   @PathVariable("userId") Integer userId,
                                   @PathVariable("comment") String comment){
        Comments comments = new Comments();
        comments.setGoodsId(goodsId);
        comments.setUserId(userId);
        comments.setComments(comment);
        commentsRepository.save(comments);
        return ResultUtil.success(ResultEnum.ADD_GOODS_COMMENT);
    }

}

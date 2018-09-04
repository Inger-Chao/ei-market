package com.inger.market.utils;

import com.inger.market.ResultEnum;
import com.inger.market.domain.Result;

/**
 * Created by IngerJo on 2018/8/31.
 * Jpa无法自动完成查询结果到自定义实体的映射，将数组元素转化为实体类
 */
public class ResultUtil {
    public static <T> Result<T> success(ResultEnum resultEnum, T resultData) {
        Result<T> result = new Result<>();
        result.setStatus(resultEnum.getStatus());
        result.setMsg(resultEnum.getMsg());
        result.setData(resultData);
        return result;
    }

    public static Result success(ResultEnum resultEnum) {
        return success(resultEnum, null);
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setStatus(resultEnum.getStatus());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

    public static Result error(Integer status, String message) {
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(message);
        return result;
    }
}

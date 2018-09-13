package com.inger.market;

/**
 * Created by IngerJo on 2018/8/31.
 */
public enum ResultEnum {
    SIGN_UP(1, "successfully to add user"),
    SIGN_IN(1, "successfully to login"),
    GET_ONE_USER_INFO(1,"successfully to get the user info"),
    GET_USER_INFO_LIST(1,"SUCCESSFULLY TO GET USERS INFO LIST"),
    GET_GOODS_INFO_LIST(1, "successfully to get goods info list"),
    GET_GOODS_TYPE_LIST(1, "successfully to get goods type list"),
    GET_ONES_CART(1, "successfully to get one's cart goods list"),
    GET_TRADE_INFO(1,"successfully get trade info"),
    DROP_TRADE(1,"successfully to delete trade of this goods"),
    ADD_CART(1, "successfully to add carts"),
    ADD_TRADE(1,"successfully to add trade"),
    DROP_CART(1, "successfully to delete carts"),
    ADD_GOODS_COMMENT(1, "successfully to add goods comment"),
    GET_GOODS_COMMENTS_LIST(1,"successfully to get goods comments"),
    GET_USER_GOODS_LIST(1, "successfully to get one's goods"),
    GET_GOODS_DETAIL(1, "successfully to get goods detail"),
    GET_GOODS_BY_KEY(1, "successfully to get goods by key"),
    GET_TYPES(1, "successfully to get types"),
    UPDATE_GOODS(1, "successfully to update goods"),
    ADD_GOODS(1, "successfully to add goods"),
    DELETE_GOODS(1, "successfully to delete goods"),
    DELETE_MORE_GOODS(1, "successfully to delete more goods"),
    GET_GOODS_COUNT(1, "successfully to get goods count"),
    UPDATE_USER(1, "successfully to update user info"),
    GET_ONES_BOUGHT_GOODS(1,"successfully to get ones bought goods"),
    CHANGE_TRADE_STATUS(1,"successfully to change trade status"),
    USER_DUPLICATE(-1, "user duplicated"),
    USER_MISSED(-1, "user missed"),
    USER_PARAMS_ERROR(-1,"post user params not correct"),
    DELETE_USER(1, " successfully to delete user"),
    WRONG_PASSWORD(-1, "wrong password"),
    PASSWORD_NOT_CHANGE(-1,"new password must different with the old"),
    PASSWORD_UPDATED(1,"successfully to change password"),
    PERMISSION_DENIED(-1, "permission denied"),
    UNKNOWN_ERROR(-1, "unknown"),
    GOODS_NOT_FOUND(-1, "goods not found"),
    TRADE_NOT_FOUND(-1,"trade not found"),;
    private Integer status;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

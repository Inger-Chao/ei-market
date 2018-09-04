package com.inger.market;

/**
 * Created by IngerJo on 2018/8/31.
 */
public enum ResultEnum {
    SIGN_UP(1, "successfully to add user"),
    SIGN_IN(1, "successfully to login"),
    GET_GOODS_INFO_LIST(1, "successfully to get goods info list"),
    GET_GOODS_TYPE_LIST(1, "successfully to get goods type list"),
    GET_GOODS_SRC_LIST(1, "successfully to get goods source list"),
    GET_GOODS_DETAIL(1, "successfully to get goods detail"),
    GET_TYPES(1, "successfully to get types"),
    UPLOAD_GOODS_FILE(1, "successfully to load goods file"),
    ADD_GOODS(1, "successfully to add goods"),
    DELETE_GOODS(1, "successfully to delete goods"),
    DELETE_MORE_GOODS(1, "successfully to delete more goods"),
    GET_GOODS_COUNT(1, "successfully to get goods count"),
    GET_TYPE_COUNT(1, "successfully to get type count"),
    USER_DUPLICATE(-1, "user duplicated"),
    USER_MISSED(-1, "user missed"),
    WRONG_PASSWORD(-1, "wrong password"),
    PERMISSION_DENIED(-1, "permission denied"),
    UNKNOWN_ERROR(-1, "unknown"),
    GOODS_NOT_FOUND(-1, "goods not found"),
    GOODS_DUPLICATE(-1, "goods duplicate"),
    ;
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

package com.inger.market.exception;

/**
 * Created by IngerJo on 2018/9/5.
 */
public class StorageException extends BaseException {
    public StorageException(Integer status, String message) {
        super(status, message);
    }

    public StorageException(Integer status, String message, Throwable cause) {
        super(status, message, cause);
    }
}

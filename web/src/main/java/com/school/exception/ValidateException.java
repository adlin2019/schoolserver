package com.school.exception;

/**
 * 用户校验失败异常类
 *
 * @author hnuer
 */
public class ValidateException extends RuntimeException {

    private String message;

    public ValidateException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

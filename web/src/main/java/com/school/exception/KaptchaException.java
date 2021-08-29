package com.school.exception;

/**
 * 验证码异常
 *
 * @author hnuer
 */
public class KaptchaException extends RuntimeException {


    private String message;


    @Override
    public String getMessage() {
        return this.message;
    }


    public KaptchaException(String message) {
        this.message = message;
    }



}

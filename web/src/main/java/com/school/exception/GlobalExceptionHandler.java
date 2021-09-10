package com.school.exception;

import com.school.pojo.ResInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 *
 * @author hnuer
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(KaptchaException.class)
    public ResInfo kaptchaException(KaptchaException e) {
        return ResInfo.error(e.getMessage());
    }

    @ExceptionHandler(ValidateException.class)
    public ResInfo validateException(ValidateException e) {
        return ResInfo.error(e.getMessage());
    }

}

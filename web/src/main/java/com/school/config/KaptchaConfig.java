package com.school.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.code.kaptcha.Constants.*;

/**
 * 验证码生成相关配置类
 *
 * @author hnuer
 */

@Configuration
public class KaptchaConfig {

    @Bean(name = "captchaProducer")
    public DefaultKaptcha captchaProducer() {

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

        // 设定配置参数
        Properties properties = new Properties();

        // 设置是否有边框
        properties.setProperty(KAPTCHA_BORDER, "yes");

        // 设置验证码颜色
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");

        // 设置验证码图片宽度
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "160");

        // 设置验证码图像高度
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");

        // 设置验证码文本字符长度
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");

        Config config = new Config(properties);

        defaultKaptcha.setConfig(config);

        return defaultKaptcha;

    }



    

}

package com.school.controller;


import com.google.code.kaptcha.Producer;
import com.school.component.RedisService;
import com.school.constant.Constants;
import com.school.pojo.ResInfo;
import com.school.utils.Base64;
import com.school.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 验证码相关接口
 *
 * @author hnuer
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisService redisService;


    /**
     * 生成验证码
     * @param response
     * @throws Exception
     */
    @RequestMapping("/getCode")
    public ResInfo getCode(HttpServletResponse response) throws Exception {

        // 1.创建唯一标识符

        String uuid = IdUtils.simpleUUID();
        // 该key作为redis中图片文本的key（唯一标识）
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        // 2.生成验证码

        // 随机得到验证码文本
        String capText = captchaProducer.createText();

        // 生成图像
        BufferedImage image = captchaProducer.createImage(capText);

        // 3.将答案存入缓存区，并设置验证码有效时间
        redisService.setCacheObject(verifyKey,capText,Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 4.向客户端响应图片信息
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();

        try {

            ImageIO.write(image, "jpg", os);

        } catch (Exception e) {

            return ResInfo.error(e.getMessage());
        }

        ResInfo resInfo = ResInfo.success();

        resInfo.put("uuid", uuid);
        resInfo.put("img", Base64.encode(os.toByteArray()));

        return resInfo;


    }






}





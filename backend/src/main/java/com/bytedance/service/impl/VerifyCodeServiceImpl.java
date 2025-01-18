package com.bytedance.service.impl;

import com.bytedance.service.VerifyCodeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import java.io.IOException;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/18 上午10:31
 * @description: VerifyCodeServiceImpl类
 */

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public byte[] generateLineCaptcha(HttpSession session) throws IOException {
        // 创建线段干扰验证码
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100, 5, 20);

        // 存储验证码文本到 Session
        session.setAttribute("verifyCode", captcha.getCode());

        System.out.println("生成线段干扰验证码: "+captcha.getCode());

        // 返回验证码图片的字节数组
        return captcha.getImageBytes();
    }

    @Override
    public byte[] generateShearCaptcha(HttpSession session) throws IOException {
        // 创建扭曲干扰验证码
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 5, 4);

        // 存储验证码文本到 Session
        session.setAttribute("verifyCode", captcha.getCode());

        System.out.println("生成扭曲干扰验证码: "+captcha.getCode());

        // 返回验证码图片的字节数组
        return captcha.getImageBytes();
    }

    @Override
    public byte[] generateCircleCaptcha(HttpSession session) throws IOException {
        // 创建圆圈干扰验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 5, 20);

        // 存储验证码文本到 Session
        session.setAttribute("verifyCode", captcha.getCode());

        System.out.println("生成圆圈干扰验证码: "+captcha.getCode());

        // 返回验证码图片的字节数组
        return captcha.getImageBytes();
    }
}
package com.bytedance.service;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/18 上午9:57
 */

public interface VerifyCodeService {
    /**
     * 生成线段干扰验证码
     *
     * @param session 用于存储验证码
     * @return 验证码图片的字节数组
     */
    byte[] generateLineCaptcha(HttpSession session) throws IOException;

    /**
     * 生成扭曲干扰验证码
     *
     * @param session 用于存储验证码
     * @return 验证码图片的字节数组
     */
    byte[] generateShearCaptcha(HttpSession session) throws IOException;

    /**
     * 生成圆圈干扰验证码
     *
     * @param session 用于存储验证码
     * @return 验证码图片的字节数组
     */
    byte[] generateCircleCaptcha(HttpSession session) throws IOException;


    /**
     * 生成GIF干扰验证码
     *
     * @param session 用于存储验证码
     * @return 验证码图片的字节数组
     */
    byte[] generateGifCaptcha(HttpSession session) throws IOException;

    /**
     * 生成数学公式验证码
     *
     * @param session 用于存储验证码
     * @return 验证码图片的字节数组
     */
    byte[] generateMathCaptcha(HttpSession session) throws IOException;
}

package com.bytedance.controller;

import com.bytedance.service.VerifyCodeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/18 上午10:47
 * @description: 验证码生成控制器
 */

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private VerifyCodeService verifyCodeService;


    /**
     * 默认生成线段干扰验证码
     */
    @GetMapping
    public void generateDefaultCaptcha(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        generateCaptcha("line", request, response);
    }


    /**
     * 根据验证码类型生成验证码
     */
    @GetMapping("/{type}")
    public void generateCaptcha(
            @PathVariable String type,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        // 设置响应类型为图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 获取 Session
        HttpSession session = request.getSession();

        // 根据类型调用不同的验证码生成方法
        byte[] captchaImageBytes = switch (type.toLowerCase()) {
            case "line" -> verifyCodeService.generateLineCaptcha(session);
            case "circle" -> verifyCodeService.generateCircleCaptcha(session);
            case "shear" -> verifyCodeService.generateShearCaptcha(session);
            default -> throw new IllegalArgumentException("不支持的验证码类型: " + type);
        };

        // 将验证码图片写入响应流
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(captchaImageBytes);
            outputStream.flush();
        }
    }
}

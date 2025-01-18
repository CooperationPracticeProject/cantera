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
            @PathVariable("type") String type,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        // 设置响应类型为图片
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);

        // 获取 Session
        HttpSession session = request.getSession();

        // 根据类型生成验证码
        byte[] captchaImageBytes;
        switch (type.toLowerCase()) {
            case "line":
                captchaImageBytes = verifyCodeService.generateLineCaptcha(session);
                break;
            case "circle":
                captchaImageBytes = verifyCodeService.generateCircleCaptcha(session);
                break;
            case "shear":
                captchaImageBytes = verifyCodeService.generateShearCaptcha(session);
                break;
            case "gif":
                captchaImageBytes = verifyCodeService.generateGifCaptcha(session);
                break;
            case "math":
                captchaImageBytes = verifyCodeService.generateMathCaptcha(session);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("不支持的验证码类型: " + type);
                return;
        }

        // 将验证码图片写入响应流
        try (OutputStream outputStream = response.getOutputStream()) {
            outputStream.write(captchaImageBytes);
            outputStream.flush();
        }
    }
}

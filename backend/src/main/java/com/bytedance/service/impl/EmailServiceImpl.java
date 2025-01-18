package com.bytedance.service.impl;

import com.bytedance.service.EmailService;
import com.bytedance.util.Email;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/18 下午4:41
 * @description: EmailServiceImpl类
 */

@Service
public class EmailServiceImpl implements EmailService {

    //读取配置文件中username的值并赋值给setPeople
    @Value("${spring.mail.username}")
    String setPeople;

    @Value("${spring.mail.password}")
    String apiKey;

    @Resource
    private Email email;


    private String loadTemplate() {
        try {
            ClassPathResource resource = new ClassPathResource("templates/email-template.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("加载邮件模板失败");
            throw new RuntimeException("加载邮件模板失败");
        }
    }

    @Override
    public boolean sendMail(String code, String getPeople) {

        String template = loadTemplate();
        String htmlContent = String.format(template, code);

        try {
            email.sendHtmlMail(getPeople,"注册验证码",htmlContent);
        } catch (Exception e) {
            System.out.println("---邮件发送异常----");
            return false;
        }
        return true;
    }
}

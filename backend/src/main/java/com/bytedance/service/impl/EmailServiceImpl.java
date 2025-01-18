package com.bytedance.service.impl;

import com.bytedance.service.EmailService;
import com.bytedance.util.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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


    @Override
    public boolean sendMail(String code, String getPeople) {

        String htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        padding: 20px;
                        font-family: Arial, sans-serif;
                    }
                    .header {
                        background-color: #4CAF50;
                        color: white;
                        padding: 20px;
                        text-align: center;
                        border-radius: 5px 5px 0 0;
                    }
                    .content {
                        background-color: #f9f9f9;
                        padding: 20px;
                        border: 1px solid #ddd;
                        border-radius: 0 0 5px 5px;
                    }
                    .code {
                        font-size: 24px;
                        font-weight: bold;
                        color: #4CAF50;
                        padding: 10px;
                        margin: 10px 0;
                        background-color: #fff;
                        border: 2px dashed #4CAF50;
                        text-align: center;
                    }
                    .notice {
                        color: #666;
                        font-size: 14px;
                        margin-top: 15px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h2>验证码通知</h2>
                    </div>
                    <div class="content">
                        <p>尊敬的用户：</p>
                        <p>您好！您的验证码为：</p>
                        <div class="code">%s</div>
                        <div class="notice">
                            <p>⚠️ 注意事项：</p>
                            <ul>
                                <li>验证码5分钟内有效</li>
                                <li>请勿将验证码泄露给他人</li>
                                <li>如非本人操作，请忽略此邮件</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(code);

        try {
            new Email().sendHtmlMail(getPeople,"注册验证码",htmlContent);
        } catch (Exception e) {
            System.out.println("---邮件发送异常----");
            return false;
        }
        return true;
    }
}

package com.bytedance.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;

@Component
public class Email {

  @Resource
  private JavaMailSender mailSender;

  @Value ("${spring.mail.username}")
  private String from;

  /**
   * 发送简单文本邮件
   *
   * @param to
   * @param subject
   * @param content
   */
  public void sendSimpleMail (String to, String subject, String content) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(content);
    mailSender.send(message);
  }

  /**
   * 发送 Html 邮件
   *
   * @param to
   * @param subject
   * @param content
   */
  @SneakyThrows
  public void sendHtmlMail (String to, String subject, String content) {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(from);
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(content, true);
    mailSender.send(message);
  }

}

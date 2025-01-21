package com.bytedance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/17 下午5:27
 * @description: AppConfig类
 */
@Configuration
public class AppConfig {

  @Bean
  public RestTemplate restTemplate () {
    return new RestTemplate();
  }

}

package com.bytedance.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/17 下午5:23
 * @description: CustomChatClient类
 */


@Service
public class CustomChatClient {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;


    private final RestTemplate restTemplate;

    public CustomChatClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String call(String message) {
        String url = baseUrl + "v3/chat/completions"; // 调整为正确的接口路径
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\n" +
                "  \"model\": \"ep-20240827152349-9pqvz\",\n" +  // 使用你指定的模型
                "  \"messages\": [{\n" +
                "    \"role\": \"user\",\n" +
                "    \"content\": \"" + message + "\"\n" +
                "  }]\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }
}


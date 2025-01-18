package com.bytedance.controller;

import com.bytedance.service.impl.CustomChatClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/17 下午4:02
 * @description: ChatController类
 */
@RestController
@RequestMapping("/ai")
public class ChatController {

    @Resource
    private final CustomChatClient chatClient;

    public ChatController(CustomChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String generate(@RequestParam(value = "message", defaultValue = "鲁迅和周树人是什么关系？") String message) {
        return chatClient.call(message);
    }
}
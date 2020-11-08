package com.example.backgroundmanager.websocket.controller;

import com.example.backgroundmanager.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author as   huangzd
 */
@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/sendAll")
    public String sendAll(String message) {
        try {
            webSocketServer.sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息
     *
     * @param message 消息内容
     * @param id      连接会话ID
     * @return
     */
    @GetMapping(value = "/sendOne")
    public String sendOneMessage(@RequestParam String message, @RequestParam String id) {
        webSocketServer.sendMessage(message, id);
        return "ok";
    }
}

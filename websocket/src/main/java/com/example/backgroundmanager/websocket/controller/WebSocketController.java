package com.example.backgroundmanager.websocket.controller;

import com.example.backgroundmanager.websocket.client.MyWebSocketClient;
import com.example.backgroundmanager.websocket.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author as   huangzd
 */
@Slf4j
@RestController
@RequestMapping("/api/websocket")
public class WebSocketController {
    @Autowired
    private MyWebSocketClient webSocketClient;

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping("/sendAll")
    public String sendAll(String message) {
        try {
            webSocketServer.sendMessageToAll(message);
            log.info("发送消息给全部用户了");
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


    @GetMapping("/sendClient")
    public String sendClient(String message) {
        webSocketClient.groupSending("测试客户端回复群发");
        log.info("发送消息给服务端了");
        return "ok";
    }
}

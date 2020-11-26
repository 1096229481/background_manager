package com.example.backgroundmanager.websocket.config;

import com.example.backgroundmanager.websocket.client.CommonWebSocketClient;
import com.example.backgroundmanager.websocket.client.MyWebSocketClient;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author as huangzd
 */
@Slf4j
@Configuration
public class WebSocketConfig {

    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 注入一个客户端
     *
     *
     */
    @Bean
    public WebSocketClient webSocketClient() {
        try {
            CommonWebSocketClient webSocketClient = new CommonWebSocketClient(new URI("ws://localhost:9091/websocket/asset"));
            webSocketClient.connect();
            return webSocketClient;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}

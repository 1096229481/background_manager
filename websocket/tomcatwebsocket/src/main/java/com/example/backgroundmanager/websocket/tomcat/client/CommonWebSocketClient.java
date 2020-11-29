package com.example.backgroundmanager.websocket.tomcat.client;

import com.example.backgroundmanager.websocket.tomcat.server.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;

/**
 * @author as huangzd
 */
@Slf4j
public class CommonWebSocketClient extends WebSocketClient {

    @Autowired
    private WebSocketServer webSocketServer;

    public CommonWebSocketClient(URI uri){
        super(uri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("------ CommonWebSocketClient onOpen ------");
    }

    @Override
    public void onMessage(String s) {
        log.info("------ CommonWebSocketClient onMessage ------");
        webSocketServer.sendMessage("接口算法的处理结果了，我要发送给前端处理","0");
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("------ CommonWebSocketClient onClose ------");
    }

    @Override
    public void onError(Exception e) {
        log.info("------ CommonWebSocketClient onError ------");
    }
}

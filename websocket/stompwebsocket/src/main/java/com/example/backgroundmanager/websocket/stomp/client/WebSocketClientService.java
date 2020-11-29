package com.example.backgroundmanager.websocket.stomp.client;

public interface WebSocketClientService {
    /**
     * 群发
     *
     * @param message
     */
    void groupSending(String message);

    /**
     * 指定发送
     *
     * @param name
     * @param message
     */
    void appointSending(String name, String message);
}

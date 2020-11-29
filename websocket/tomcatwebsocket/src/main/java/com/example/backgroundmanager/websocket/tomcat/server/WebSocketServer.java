package com.example.backgroundmanager.websocket.tomcat.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author as huangzd
 */

@ServerEndpoint("/websocket/asset")
@Component
@Slf4j
public class WebSocketServer {
    /**
     * 记录当前在线连接数,需线程安全
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的Session对象
     */
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id) {
        sessionSet.add(session);
        onlineCount.incrementAndGet(); // 在线数加1
        String message = String.format("有新连接加入：%s，当前在线人数为：%s", session.getId(), onlineCount.get());
        try {
            //有新连接，将信息发送给所有已连接的人员,或者可以改为将自己的id返回给用户
            sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("发送消息给用户失败");
        }
        log.info(message);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        onlineCount.decrementAndGet(); // 在线数减1
        String message = String.format("有一连接关闭：%s，当前在线人数为：%s", session.getId(), onlineCount.get());
        try {
            //有新连接退出，将信息发送给所有已连接的人员
            sendMessageToAll(message);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("发送消息给用户失败");
        }
        log.info(message);
    }

    /**
     * 收到客户端消息后调用的方法
     * 只能接收string类型，前端可以传json字符串过来，后台解析成对象处理
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);
        this.sendMessage("Hello, " + message, session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, toSession.getId()));
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{}", e);
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessageToAll(String message) throws IOException {
        for (Session session : sessionSet) {
            if (session.isOpen()) {
                sendMessage(message, session);
            }
        }
    }

    public void sendMessage(String message, String sessionId) {
        Session session = null;
        for (Session s : sessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(message, session);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }

    }
}

package com.konasl.blockchain.tms.core.service;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocketIOService implements InitializingBean {

    private final SocketIOServer socketIOServer;
    private final SocketIONamespace socketIONamespace;

    public SocketIOService(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
        this.socketIONamespace = socketIOServer.addNamespace("/common");
    }

    @Override
    public void afterPropertiesSet() {
        this.socketIONamespace.addConnectListener(onConnected());
        this.socketIONamespace.addEventListener("message", String.class, onMessage());
        this.socketIONamespace.addDisconnectListener(onDisconnected());
        socketIOServer.start();
    }

    private ConnectListener onConnected() {
        return socketIOClient -> {
            String sessionId = socketIOClient.getSessionId().toString();
            log.info("Client[{}] connected", sessionId);
        };
    }

    private DisconnectListener onDisconnected() {
        return socketIOClient -> {
            String sessionId = socketIOClient.getSessionId().toString();
            log.info("Client[{}] disconnected", sessionId);
        };
    }

    private DataListener<String> onMessage() {
        return (client, data, ackSender) -> {
            log.info("Received data: {}", data);
            client.sendEvent("message", data);
        };
    }
}

package com.gwork.webapp.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class GchatWebSocketHandler extends TextWebSocketHandler{
    
	private static final Logger LOGGER = LoggerFactory.getLogger(GchatWebSocketHandler.class);
	
    private final static List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<WebSocketSession>());
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	LOGGER.debug(" receive message......"+message.getPayload());
        super.handleTextMessage(session, message);
        TextMessage rtnMsg = new TextMessage(" had received ");
        session.sendMessage(rtnMsg);
    }
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	LOGGER.debug("connect to the websocket chat success......");
        sessions.add(session);
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        LOGGER.debug("websocket chat connection closed......");
        sessions.remove(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	LOGGER.debug("websocket chat connection closed......");
        sessions.remove(session);
    }

    
    
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
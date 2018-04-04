package com.dariojolo.backend;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyMessageHandler extends TextWebSocketHandler {
		 
	    @Override
	    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	        // The WebSocket has been closed
	    }
	 
	    @Override
	    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	        // The WebSocket has been opened
	        // I might save this session object so that I can send messages to it outside of this method
	 
	        // Let's send the first message
	        session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
	    }
	 
	    @Override
	    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
	        // A message has been received
	        System.out.println("Message received: " + textMessage.getPayload());
	    }
	}

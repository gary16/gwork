package com.gwork.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.gwork.webapp.handler.GchatWebSocketHandler;
import com.gwork.webapp.interceptor.GhandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class GwebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getGchatWebSocketHandler(),"/coordination").withSockJS();
    }
    
    @Bean
    public GchatWebSocketHandler getGchatWebSocketHandler() {
        return new GchatWebSocketHandler();
    }
    
    @Bean
    public GhandshakeInterceptor getGhandshakeInterceptor(){
        return new GhandshakeInterceptor();
    }
}
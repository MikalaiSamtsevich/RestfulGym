package com.api.restfulgym.websocket;

import com.api.restfulgym.dto.MyMessage;
import com.api.restfulgym.service.UserService;
import com.api.restfulgym.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class NotificationController {
    SimpMessagingTemplate simpMessagingTemplate;
    UserService userService;
    JwtUtils jwtUtils;

//    @SubscribeMapping("/user/specific")
//    public void subscribe(@Header("Authorization") String authHeader, Message<?> message) {
//        StompHeaderAccessor accessor =
//                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//        log.info("subscribe: {}", message);
//        String token = authHeader.substring(7);
//        String username = jwtUtils.getUsername(token);
//        UserDetails userDetails = userService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        assert accessor != null;
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        accessor.setUser(usernamePasswordAuthenticationToken);
//    }

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public MyMessage send(final MyMessage message) {
        return message;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload MyMessage message) {
        log.info("Message received {}", message);
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
    }
}

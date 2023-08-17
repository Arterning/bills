package cn.ning.money.book.controller.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/send/message")
    @SendTo("/topic/messages")
    public String greeting(String message) {
        return "Hello, " + message + "!";
    }

}

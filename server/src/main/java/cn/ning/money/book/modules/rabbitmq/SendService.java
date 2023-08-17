package cn.ning.money.book.modules.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 消息发送服务
 */
@RequiredArgsConstructor
@Service
public class SendService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend("topicBasketBall", msg);
    }

    public void sendTopicMsg(String msg, String route) {
        rabbitTemplate.convertAndSend("topicExchange", route, msg);
    }

}

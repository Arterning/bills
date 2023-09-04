package cn.ning.money.book.modules.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    /**
     * 篮球消息队列
     * @return
     */
    @Bean
    public Queue topicBasketBall() {
        return new Queue("topicBasketBall", true);
    }

    /**
     * 图片队列
     * @return
     */
    @Bean
    public Queue topicImages() {
        return new Queue("topicImages", true);
    }

    /**
     * 日志队列
     * @return
     */
    @Bean
    public Queue topicLogs() {
        return new Queue("topicLogs", true);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }


    /**
     * 队列绑定到交换机
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicImages()).to(topicExchange())
                .with("topicExchange.topicImages");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicLogs()).to(topicExchange())
                .with("topicExchange.topicLogs");
    }
}

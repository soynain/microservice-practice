package com.oauthserver.auth.AuthServerConfiguration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class QueueComponentsConfig {
    public final static String QUEUE_NAME = "send-product-query-petition-queue";
    private final static String QUEUE_RSP_NAME = "send-reply-back-product-query";
    public final static String TOPIC_EXCHANGE_NAME = "product-query-exchange";

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(QUEUE_RSP_NAME);
        template.setReplyTimeout(6000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_RSP_NAME);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }

    @Bean(name="msgQueue")
    public Queue msgQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = "replyQueue")
    public Queue replyQueue() {
        return new Queue(QUEUE_RSP_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    Binding bindingSending(@Qualifier("msgQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean
    Binding bindingReply(@Qualifier("replyQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_RSP_NAME);
    }

}

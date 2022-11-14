package com.productsquerymicroservice.microservice.Configurations;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class QueueReceiverAndResponseConf {
    public final static String QUEUE_NAME = "send-product-query-petition-queue";
    public final static String QUEUE_RSP_NAME="send-reply-back-product-query";
    public final static String TOPIC_EXCHANGE_NAME = "product-query-exchange";

    @Bean(name = "msgQueue")
    public Queue msgQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean(name = "replyQueue")
    public Queue replyQueue() {
        return new Queue(QUEUE_RSP_NAME);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean(name = "bindingSending")
    Binding bindingSending(@Qualifier("msgQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange()).with(QUEUE_NAME);
    }

    @Bean(name = "bindingReply")
    Binding bindingReply(@Qualifier("replyQueue") Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_RSP_NAME);
    }
}

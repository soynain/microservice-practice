package com.productsquerymicroservice.microservice.Components;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productsquerymicroservice.microservice.Configurations.QueueReceiverAndResponseConf;
import com.productsquerymicroservice.microservice.Services.DAOProductQueryImpl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter   
@Component
public class ProductQueryRequestReader{
    //private CountDownLatch latch=new CountDownLatch(0);
    //private String messageFromAuthEventString;
    //private static final Logger log=LoggerFactory.getLogger(ProductQueryRequestReader.class);

    /*https://medium.com/geekculture/spring-boot-rabbitmq-implements-rpc-calls-f795e72e52d7
     * 
     * GREAT RESOURCE TO KNOW HOW TO IMPLEMENT A SAGA PATTERN. 
     * In rabbit MQ, technically this is called an RPC messaging mode.
     * That is ofcourse, if you plan on returning a message as an answer.
     * 
     * For example, to send an event you just need the instance method
     * convertAndSend(), and the other microservice would need
     * to trigger another trigger by listening to certain topic exchange
     * and queue, and so like that.
    */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    DAOProductQueryImpl productQueryImpl;

    private ObjectMapper jsonConverter=new ObjectMapper();


    @RabbitListener(queues = QueueReceiverAndResponseConf.QUEUE_NAME)
    public void receiveMessage(Message messageFromAuthEventString) throws JsonProcessingException {
        //byte[] body = messageFromAuthEventString.getBody();
        //This is the message to be returned by the server
        
        String jsonProductQueryResponse=jsonConverter.writeValueAsString(productQueryImpl.getAllProducts());
        //log.info("JSON RESULTANTE: "+jsonProductQueryResponse);
        Message build = MessageBuilder.withBody(jsonProductQueryResponse.getBytes()).build();
        CorrelationData correlationData = new CorrelationData(messageFromAuthEventString.getMessageProperties().getCorrelationId());
        rabbitTemplate.sendAndReceive(QueueReceiverAndResponseConf.TOPIC_EXCHANGE_NAME, QueueReceiverAndResponseConf.QUEUE_RSP_NAME, build, correlationData);
    }


}

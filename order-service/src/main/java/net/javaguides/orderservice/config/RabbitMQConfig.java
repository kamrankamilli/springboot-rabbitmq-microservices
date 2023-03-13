package net.javaguides.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.order.name}")
    private String orderQueue;

    @Value("${rabbitmq.queue.email.name}")
    private String emailQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.order}")
    private String orderRoutingKey;


    @Value("${rabbitmq.routing.key.email}")
    private String emailRoutingKey;

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }

    @Bean
    public TopicExchange setExchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue()).to(setExchange()).with(orderRoutingKey);
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(setExchange()).with(emailRoutingKey);
    }

    @Bean
    public MessageConverter setConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate setAmqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(setConverter());
        return rabbitTemplate;
    }



}

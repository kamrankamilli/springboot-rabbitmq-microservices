package net.javaguides.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.orderservice.modal.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.order}")
    private String orderRoutingKey;

    @Value("${rabbitmq.routing.key.email}")
    private String emailRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(OrderEvent orderEvent){
        log.info(String.format("Order event send -> %s",orderEvent));
        rabbitTemplate.convertAndSend(exchange,orderRoutingKey,orderEvent);

        rabbitTemplate.convertAndSend(exchange,emailRoutingKey,orderEvent);
    }
}

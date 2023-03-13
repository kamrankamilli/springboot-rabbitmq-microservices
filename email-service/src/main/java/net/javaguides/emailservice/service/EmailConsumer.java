package net.javaguides.emailservice.service;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.emailservice.modal.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consume(OrderEvent orderEvent){
        log.info(String.format("Order event received -> %s",orderEvent));
    }
}

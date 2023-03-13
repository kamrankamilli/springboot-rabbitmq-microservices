package net.javaguides.stockservice.service;


import lombok.extern.slf4j.Slf4j;
import net.javaguides.stockservice.modal.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockConsumer {

    @RabbitListener(queues = {"${rabbitmq.queue.order.name}"})
    public void consume(OrderEvent orderEvent){
        log.info(String.format("Order Event received -> %s",orderEvent));
    }
}

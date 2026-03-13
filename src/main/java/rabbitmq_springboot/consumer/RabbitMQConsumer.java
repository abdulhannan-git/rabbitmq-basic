package rabbitmq_springboot.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbitmq_springboot.dto.User;

@Service
public class RabbitMQConsumer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message) {
        logger.info(String.format("Received message from -> %s", message));
    }

    @RabbitListener(queues = {"${rabbitmq.json_queue.name}"})
    public void consume(User user) {
        logger.info(String.format("Received Json from -> %s", user));
    }
}

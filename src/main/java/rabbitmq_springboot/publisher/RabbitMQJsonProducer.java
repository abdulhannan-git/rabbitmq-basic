package rabbitmq_springboot.publisher;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rabbitmq_springboot.dto.User;

@Service
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private final RabbitTemplate rabbitTemplate;

//    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    public void sendJsonMessage(User user) {
        logger.info(String.format("Json Message sent -> %s", user));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }
}

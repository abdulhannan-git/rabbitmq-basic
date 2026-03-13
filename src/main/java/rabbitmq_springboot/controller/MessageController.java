package rabbitmq_springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabbitmq_springboot.dto.User;
import rabbitmq_springboot.publisher.RabbitMQJsonProducer;
import rabbitmq_springboot.publisher.RabbitMQProducer;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MessageController {

    private final RabbitMQProducer producer;

    private final RabbitMQJsonProducer jsonProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ....!");
    }

    @PostMapping("/json-publish")
    public ResponseEntity<User> sendJsonMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}

package rabbitmq_springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class rabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.json_queue.name}")
    private String json_queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingkey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(json_queue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingkey);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}

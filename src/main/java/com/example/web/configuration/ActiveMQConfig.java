package com.example.web.configuration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfig {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQConfig.class);

    @Value("${spring.activemq.broker-url}")
    private String brokerURL;
    @Value("${spring.activemq.user}")
    private String user;
    @Value("${spring.activemq.password}")
    private String pass;

    @Bean
    public Queue queue() {
        logger.info("Accessed queue method");
        return new ActiveMQQueue("publisher.queue");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        logger.info("Accessed ActiveMQConnectionFactory method");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerURL);
        factory.setUserName(user);
        factory.setPassword(pass);
        logger.info("ActiveMQ connection done successfully");
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        logger.info("Accessed JmsTemplate method");
        return new JmsTemplate(activeMQConnectionFactory());
    }
}

package com.example.web.listener;

import com.example.web.model.ResourceModel;
import com.example.web.resource.ProducerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Base64;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "publisher.queue")
    public void listener(byte[] message) throws IOException, ClassNotFoundException {

        logger.info("Accessed listener method of consumer class");
        logger.info("Recieved Message: "+message);

        ResourceModel decodedString = new ResourceModel();
        decodedString = convertFromBytes(Base64.getDecoder().decode(message));

        logger.info("Decoded Message: "+decodedString);

        if(ProducerResource.originalMsg.equals(decodedString))
        {
            logger.info("Input text and Output text are same.");
        }
        else
        {
            logger.info("Input text and Output text are different.");
        }
    }

    private ResourceModel convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (ResourceModel) in.readObject();
        }
    }
}

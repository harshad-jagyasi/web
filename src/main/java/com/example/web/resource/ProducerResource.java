package com.example.web.resource;

import com.example.web.model.ResourceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {

    Logger logger = LoggerFactory.getLogger(ProducerResource.class);

    @Autowired
    JmsTemplate JmsTemplate;

    @Autowired
    Queue queue;

    public static ResourceModel originalMsg;

    public String publish(final ResourceModel messageModel) throws IOException {
        logger.info("publish method of ProducerResource class is accessed");

        originalMsg = messageModel;
        logger.info("Original Message: "+originalMsg);

        byte[] encodedmsg = Base64.getEncoder().encode(convertToBytes(originalMsg));
        logger.info("Encoded Message: "+encodedmsg);

        JmsTemplate.convertAndSend(queue, encodedmsg);
        logger.info("Queue published successfully");

        return "Published Successfully";
    }

    private byte[] convertToBytes(ResourceModel resMod) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos))
        {
            out.writeObject(resMod);
            return bos.toByteArray();
        }
    }
}
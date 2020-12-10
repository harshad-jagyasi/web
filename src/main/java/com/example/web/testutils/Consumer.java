package com.example.web.testutils;

import org.slf4j.Logger;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.junit.Assert.assertNotNull;

public class Consumer {
    //private static final Logger = getLogger(Consumer.class);
    private List<String> consumedMessage = new ArrayList<>();
    private MessageConsumer consumer;

    public Consumer(Session session, Destination destination) throws Exception{
        checkNotNull(session,"Session should be initialized");
        checkNotNull(destination, "Destination should be initialized");
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(listener());
    }

    private MessageListener listener(){
        return (message)->{
            if(message instanceof TextMessage){
                TextMessage msg = (TextMessage) message;
                assertNotNull("Message should not be null", message);
                try{
                    consumedMessage.add(msg.getText());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(message!=null){
                //LOGGER
            }
        };
    }

    public List<String> getConsumedMessage(){
        return consumedMessage;
    }
}

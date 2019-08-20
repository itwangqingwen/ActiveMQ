package com.imooc.jms.procedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;


public class ProcedureServiceImpl implements ProcedureService {
    @Autowired
     JmsTemplate jmsTemplate;
    @Resource(name="topicDestination")
    Destination destination;
    public void sendMessage(final String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message1 = session.createTextMessage(message);
                return message1;
            }
        });
        System.out.println("发送了"+message);
    }
}

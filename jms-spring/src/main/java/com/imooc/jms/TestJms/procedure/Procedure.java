package com.imooc.jms.TestJms.procedure;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.JmsException;

import javax.jms.*;

public class Procedure {
    private final static String url = "tcp://127.0.0.1:61616";
    private final String queueName = "queue_name";
    public static void main(String[] args) throws  JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("queueName");

        MessageProducer producer = session.createProducer(destination);
        for (int i= 0;i<50;i++){
            TextMessage textMessage = session.createTextMessage("发送消息"+i);
            producer.send(textMessage);
            System.out.println("发送消息"+i);
        }
        if(null!=connection){
            connection.close();
        }
    }

}

package com.imooc.topicTest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicReceiver {
    private static final String url = "tcp://127.0.0.1:61616";
    private static final String TopicName = "toptest";
    public static void main(String[] args) throws  JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

            Connection connection = connectionFactory.createConnection();
            connection.start();
            System.out.println("获取连接成功");
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            System.out.println("创建Session成功");
            Destination destination = session.createTopic(TopicName);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                   TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("获取信息"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }


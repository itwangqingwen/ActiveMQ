package com.imooc.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumerTopic {
    private static final String url = "tcp://127.0.0.1:61616";
    private static final String TopicName = "topic";

    public static void main(String[] args) {
        Connection connection = null;
        //1. 创建一个ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2. 创建一个Connection
        try {
            connection = factory.createConnection();
            System.out.println("获取连接成功");
            connection.start();
            //3. 创建一个Session
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //4. 创建一个目的地destination
            Destination destination = session.createTopic(TopicName);
            //5. 创建MessageProvider MessageConsumer  Message
            MessageConsumer consumer = session.createConsumer(destination);

            //6. 创建一个监听器

            consumer.setMessageListener(new MessageListener() {

                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;

                    try {
                        System.out.println("接受消息："+textMessage.getText());

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            System.out.println("获取连接失败");
        }

    }
}

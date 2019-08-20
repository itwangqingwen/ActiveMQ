package com.imooc.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProvider {

    private static final String url = "tcp://127.0.0.1:61616";
    private static final String queneName = "test";

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
            Destination destination = session.createQueue(queneName);
            //5. 创建MessageProvider MessageConsumer  Message
            MessageProducer messageProducer= session.createProducer(destination);
            for (int i=0;i<100;i++){
                TextMessage textMessage = session.createTextMessage("TEXT"+i);
                messageProducer.send(textMessage);
                System.out.println("消息发送："+textMessage.getText());
                }
        } catch (JMSException e) {
            System.out.println("获取连接失败");
        }finally {
            try {
                if(connection!=null) connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

}


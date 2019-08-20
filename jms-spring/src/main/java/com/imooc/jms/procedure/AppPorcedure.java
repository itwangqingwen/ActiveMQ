package com.imooc.jms.procedure;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.TextMessage;

public class AppPorcedure {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("procedure.xml");
        ProcedureService service = context.getBean(ProcedureService.class);
        for (int i = 0;i<50;i++){
            service.sendMessage("消息："+i);
        }
        ((ClassPathXmlApplicationContext) context).close();
    }
}

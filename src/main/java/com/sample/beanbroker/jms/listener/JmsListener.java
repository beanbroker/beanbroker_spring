package com.sample.beanbroker.jms.listener;

import com.sample.beanbroker.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JmsListener {


    //TODO 분석 서비스 추가하여 여기서 돌아가야함
    private static Logger log = LoggerFactory.getLogger(JmsListener.class);
    @org.springframework.jms.annotation.JmsListener(destination = "beanbroker-queue", containerFactory = "jsaFactory")
    public void receiveMessage(Customer customer){

        log.info("test {}" , customer.toString());

    }
}

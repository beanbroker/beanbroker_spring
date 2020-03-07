package com.sample.beanbroker.jms.consumer;

import com.sample.beanbroker.model.Customer;
import com.sample.beanbroker.model.MessageStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class JmsConsumer {
	private static Logger log = LoggerFactory.getLogger(JmsConsumer.class);
	@Autowired
	private MessageStorage customerStorage;

	@JmsListener(destination = "beanbroker-queue", containerFactory="jsaFactory")
	public void receive(Customer customer){
		log.info("1111111111111Recieved Message: " + customer);
//		customerStorage.add(customer);
	}

	@JmsListener(destination = "board-count-up-queue", containerFactory="jsaFactory")
	public void erer(Object ob){
		log.info("22222222 Message: " + ob);
//		customerStorage.add(customer);
	}
}
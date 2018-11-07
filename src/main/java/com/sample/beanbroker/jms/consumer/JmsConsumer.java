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
	
	@JmsListener(destination = "${beanbroker.activemq.queue}", containerFactory="jsaFactory")
	public void receive(Customer customer){
		log.debug("Recieved Message: " + customer);
		customerStorage.add(customer);
	}
}
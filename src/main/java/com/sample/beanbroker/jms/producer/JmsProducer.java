package com.sample.beanbroker.jms.producer;

import com.sample.beanbroker.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class JmsProducer {
	private static Logger log = LoggerFactory.getLogger(JmsProducer.class);
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${beanbroker.activemq.queue}")
	String queue;
	
	public void send(Customer customer){

		log.debug("send data : {} " , customer.toString());
		jmsTemplate.convertAndSend(queue, customer);
	}
}
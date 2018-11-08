package com.sample.beanbroker.controller;

import com.sample.beanbroker.jms.producer.JmsProducer;
import com.sample.beanbroker.model.Customer;
import com.sample.beanbroker.model.MessageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MqController {
	
	@Autowired
	JmsProducer jmsProducer;
	
	@Autowired
	private MessageStorage messageStorage;


	@PostMapping(value="/mq/customer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer postCustomer(@RequestBody Customer customer){
		jmsProducer.send(customer);

		return customer;
	}
	
	@GetMapping(value="/mq/customers")
	public List<Customer> getAll(){
		List<Customer> customers = messageStorage.getAll();
		return customers;
	}
	
	@DeleteMapping(value="/mq/customers/clear")
	public String clearCustomerStorage() {
		messageStorage.clear();
		return "Clear All CustomerStorage!";
	}




}

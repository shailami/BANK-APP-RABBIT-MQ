package com.cg.app.transaction.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cg.app.transaction.entity.Transaction;
@Component
@Lazy
public class Sender {
	@Autowired
	RabbitMessagingTemplate template;
	
	@Bean
	public Queue queue() {
		return new Queue("CustomerQ",false);
	}
	
	public void send(Transaction transaction) {
		template.convertAndSend("CustomerQ", transaction);
	}
}

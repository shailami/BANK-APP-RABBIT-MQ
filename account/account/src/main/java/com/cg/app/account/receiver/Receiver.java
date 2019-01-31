package com.cg.app.account.receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.cg.app.account.resource.AccountResource;
import com.cg.app.transaction.entity.Transaction;

@Component
public class Receiver {
	@Autowired
	AccountResource resource;
	@Bean
	public Queue queue() {
		return new Queue("CustomerQ", false);
	}

	@RabbitListener(queues = "CustomerQ")
	public void processMessage(Transaction transaction) {
		System.out.println(transaction);
		resource.updateBalance(transaction.getAccountNumber(), transaction.getCurrentBalance());
	}
}

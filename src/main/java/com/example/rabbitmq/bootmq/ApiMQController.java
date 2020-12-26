package com.example.rabbitmq.bootmq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.websocket.server.PathParam;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.bootmq.beans.Person;

@RestController
@RequestMapping("/rabbitmq")
public class ApiMQController {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Value("${rabbitmq.direct.exchange}")
	String directExchange;
	
	@Value("${rabbitmq.topic.exchange}")
	String topicExchange;
	
	@Value("${rabbitmq.routingkey}")
	private String routingkey;
	
	@GetMapping("/publish/{name}")
	public String publishStatus(@PathVariable("name") String name) {
		System.out.println("Request param is :" +name);
		Person person = new Person(1l,name);
		rabbitTemplate.convertAndSend("Queue1", person);
		return "success";
	}

	@GetMapping("/headerExchane/{name}")
	public String publishHeaderEExchangeStatus(@PathVariable("name") String name) throws IOException {
		System.out.println("Request param is :" +name);
		Person person = new Person(1l,name);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
		objectOutputStream.writeObject(person);
		objectOutputStream.flush();
		objectOutputStream.close();
		byte[] messageInBytes = arrayOutputStream.toByteArray();
		arrayOutputStream.close();
		
		Message message = MessageBuilder.withBody(messageInBytes)
				.setHeader("item1", "Queue1")
				.setHeader("item2", "Queue2")
				.build();
		rabbitTemplate.send("Headers_Exchange", "", message);
		return "success";
	}
	
	@GetMapping("/programaticCreation/{name}")
	public String publishMessageThroughDynamicCreation(@PathVariable("name") String name) {
		System.out.println("Request param is :" +name);
		Person person = new Person(1l,name);
		rabbitTemplate.convertAndSend(directExchange, routingkey, person);
		return "message published successfully through programmatic creation";
	}
	
	@GetMapping("/programaticCreationTopicExchange/{name}")
	public String publishMessageThroughDynamicCreationTopicExchange(@PathVariable("name") String name) {
		System.out.println("Request param is :" +name);
		Person person = new Person(1l,name);
		rabbitTemplate.convertAndSend(topicExchange, "mobile.tv", person);
		return "message published successfully through programmatic creation";
	}

}

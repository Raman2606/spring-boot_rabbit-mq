package com.example.rabbitmq.bootmq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.rabbitmq.bootmq.beans.Person;

@Component
public class MQConsumers {

	
//	@RabbitListener(queues = "Queue1")
//	public void getMessagesFromQueue1(Person person) {
//		System.out.println(person);
//		System.out.println("Message from queue : " + person.getName());
//	}
//	
//	@RabbitListener(queues = "Queue1")
//	public void getMessagesFromQueue1Consumer1(byte[] person) throws IOException, ClassNotFoundException {
//		System.out.println(person);
//		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(person);
//		ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
//		Person person2 = (Person)inputStream.readObject();
//		arrayInputStream.close();
//		inputStream.close();
//		System.out.println("Message from Header EXchange on queue1 : " + person2.getName());
//	}
//	
//	@RabbitListener(queues = "Queue1")
//	public void getMessagesFromQueue1Consumer2(byte[] person) throws IOException, ClassNotFoundException {
//		System.out.println(person);
//		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(person);
//		ObjectInputStream inputStream = new ObjectInputStream(arrayInputStream);
//		Person person2 = (Person)inputStream.readObject();
//		arrayInputStream.close();
//		inputStream.close();
//		System.out.println("Message from Header EXchange on queue1 COnsumer2: " + person2.getName());
//	}
}

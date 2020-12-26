package com.example.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {

	/*
	 * public static void main(String[] args) throws IOException, TimeoutException {
	 * 
	 * ConnectionFactory connectionFactory = new ConnectionFactory(); Connection
	 * connection = connectionFactory.newConnection(); Channel channel =
	 * connection.createChannel(); DeliverCallback deliverCallback =(consumerTag,
	 * message) -> { String messageFromQueue = new String(message.getBody());
	 * System.out.println("Message from the queue2 is : "+ messageFromQueue); };
	 * channel.basicConsume("Queue2", true,deliverCallback, (consumerTag)->{}); }
	 */

}

package com.example.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

	
	/*
	 * public static void main(String[] args) throws IOException, TimeoutException {
	 * ConnectionFactory connectionFactory = new ConnectionFactory(); Connection
	 * connection = connectionFactory.newConnection();
	 * 
	 * Channel channel = connection.createChannel(); String message[] =
	 * {"First message from RabbitMQ","Second message from RabbitMQ"
	 * ,"Third message from RabbitMQ","Fourth message from RabbitMQ"}; for(String
	 * msg : message) { System.out.println("Message published from publisher : "+
	 * msg); channel.basicPublish("", "Queue1", null, msg.getBytes()); }
	 * channel.close(); connection.close(); }
	 */
}

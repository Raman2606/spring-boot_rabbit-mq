package com.example.rabbitmq.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class RabbitMQConfiguration {
	
	@Value("${rabbitmq.queue.mobile}")
	public String mobileQueueName;
	
	@Value("${rabbitmq.queue.tv}")
	String TVQueueName;
	
	@Value("${rabbitmq.queue.ac}")
	String ACQueueName;
	
	@Value("${rabbitmq.direct.exchange}")
	String directExchange;
	
	@Value("${rabbitmq.fanout.exchange}")
	String fanoutExchange;
	
	@Value("${rabbitmq.topic.exchange}")
	String topicExchange;
	
	
	@Value("${rabbitmq.header.exchange}")
	String headerExchange;

	@Value("${rabbitmq.routingkey}")
	private String routingkey;
	
	@Bean
	@Primary
	Queue mobileQueue() {
		return new Queue(mobileQueueName, true);
	}
	
	@Bean
	Queue tvQueue() {
		return new  Queue(TVQueueName, true);
	}
	
	@Bean
	Queue acQueue() {
		return new  Queue(ACQueueName, true);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(directExchange);
	}
	
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(fanoutExchange, true, false);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(topicExchange, true, false);
	}
	
	@Bean
	HeadersExchange headersExchange() {
		return new HeadersExchange(headerExchange, true, false);
	}

//	@Bean
//	List<Binding> bindings() {
//		return Arrays.asList(
//				BindingBuilder.bind(mobileQueue()).to(exchange()).with("mobile"),
//				BindingBuilder.bind(tvQueue()).to(exchange()).with("tv"),
//				BindingBuilder.bind(acQueue()).to(exchange()).with("ac"));
//	}
	@Bean
	public Declarables directExchangeBindings( ) {
		return new Declarables(
				mobileQueue(),
				tvQueue(),
				acQueue(),
				exchange(),
				BindingBuilder.bind(mobileQueue()).to(exchange()).with("mobile"),
				BindingBuilder.bind(tvQueue()).to(exchange()).with("tv"),
				BindingBuilder.bind(acQueue()).to(exchange()).with("ac")
				);
	}
	
	
	
	
	@Bean
	public Declarables topicExchangeBindings( ) {
		return new Declarables(
				mobileQueue(),
				tvQueue(),
				acQueue(),
				topicExchange(),
				BindingBuilder.bind(mobileQueue()).to(topicExchange()).with("mobile.*"),
				BindingBuilder.bind(tvQueue()).to(topicExchange()).with("#.tv"),
				BindingBuilder.bind(acQueue()).to(topicExchange()).with("*.*.ac")
				);
	}
	
	
	/**
	 * header exchange binding
	 * whereAll/whereAny- can be of 2 types- > key with values/array of keys only
	 * @return
	 */
	@Bean
	public Declarables headerExchangeBindings( ) {
		HeadersExchange exchange = headersExchange();
		Map<String,Object> headerValues = new LinkedHashMap<String, Object>();
		headerValues.put("product", "ac");
		headerValues.put("org", "xyz");
		
		return new Declarables(
				mobileQueue(),
				tvQueue(),
				acQueue(),
				exchange,
				BindingBuilder.bind(mobileQueue()).to(exchange).where("product").matches("mobile"),
				BindingBuilder.bind(acQueue()).to(exchange).whereAll(headerValues).match(),
			    BindingBuilder.bind(tvQueue()).to(exchange).whereAny(new String[] {"tv","television"}).exist()

				);
	}
	
	
	
	
}

package org.imartynov.quote.input;

import org.imartynov.quote.input.initializers.IndexProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(IndexProperties.class)
public class QuoteInputApplication {
	private static final Logger LOG = LoggerFactory.getLogger(QuoteInputApplication.class);

	@Autowired
	private IndexProperties properties;

	static final String topicName = "input";		// where we sent events


//	@Bean
//	FanoutExchange exchange() {
//		return new FanoutExchange(exchangeName);
//	}
//
//	@Bean
//	public Declarables queues() {
//		return new Declarables(
//			IntStream.range(1, properties.getFanoutCount() + 1).boxed().map(n -> String.format("input_%d", n)).map(
//					n -> new Queue(n, false, false, true)).collect(Collectors.toList())
//		);
//	}


//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//											 MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter);
//		container.setConcurrency("3-10");
//		return container;
//	}
//

	public static void main(String[] args) {
		// Launch the application
		SpringApplication.run(QuoteInputApplication.class, args);
	}

}

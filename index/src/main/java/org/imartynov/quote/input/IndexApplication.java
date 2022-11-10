package org.imartynov.quote.input;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.imartynov.quote.input.initializers.IndexProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(IndexProperties.class)
public class IndexApplication {
	private static final Logger LOG = LoggerFactory.getLogger(IndexApplication.class);

	@Autowired
	private IndexProperties properties;

	@Autowired
	private KafkaProperties kafkaProperties;

	static final String topicName = "input";		// where we sent events

//	@Bean
//	ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory(ConsumerFactory<Integer, String> consumerFactory) {
//		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory);
//		return factory;
//	}
//
//	@Bean
//	public ConsumerFactory<Integer, String> consumerFactory() {
//		return new DefaultKafkaConsumerFactory<>(consumerProps());
//	}
//
//	private Map<String, Object> consumerProps() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-1:9092");
//		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
//		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.imartynov.quote.stock.QuoteDeserializer.class);
//		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		props.put(ConsumerConfig., "earliest");
//		// ...
//		return props;
//	}

	public static void main(String[] args) {
		// Launch the application
		SpringApplication.run(IndexApplication.class, args);
	}

}

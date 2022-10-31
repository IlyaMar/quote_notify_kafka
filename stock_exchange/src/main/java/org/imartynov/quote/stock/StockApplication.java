package org.imartynov.quote.stock;

import org.apache.kafka.clients.admin.NewTopic;
import org.imartynov.quote.stock.initializers.StockExchangeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
//@EnableJms
@EnableKafka
@EnableConfigurationProperties(StockExchangeProperties.class)
public class StockApplication implements ApplicationRunner {
	private static final Logger LOG = LoggerFactory.getLogger(StockApplication.class);

	static final String topicOut = "stock_exchange";	// topic where we publish quotes

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(topicOut)
				.partitions(8)
				.replicas(1)
				.build();
	}

	public static void main(String[] args) {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(StockApplication.class, args);
		context.close();
	}

	@Override
	public void run(ApplicationArguments args) {
		LOG.info("Stock exchange started : {}", args);
	}


}

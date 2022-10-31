package org.imartynov.quote.stock;

import org.imartynov.quote.stock.dto.Quote;
import org.imartynov.quote.stock.initializers.StockExchangeProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class Runner implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Runner.class);

    private final Producer producer;

    private final StockExchangeProperties properties;
//    Producer<String, String> producer;

    @Autowired
    public Runner(StockExchangeProperties properties, Producer producer) {
        this.properties = properties;
        this.producer = producer;
        LOG.info("init with properties: {}", properties);

//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("linger.ms", 1);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producer = new KafkaProducer<>(props);
    }

    @PreDestroy
    public void destroy() {
        LOG.info("PreDestroy.");
//        producer.close();
    }

    @Override
    public void run(String... args) throws Exception {
        final int mps = properties.getQuotePerSecond();        // messages per second
        LOG.info("Sending quotes, {} per second", mps);
        long t0 = System.currentTimeMillis();
        int n = 0;      // message counter
        int l = 0;      // message counter for display
        while (true) {
//            rabbitTemplate.convertAndSend(StockApplication.topicExchangeOut, "foo.bar.baz", new Quote("SBRF", 100.0));
            producer.sendMessage(new Quote("SBRF", 100.0));
            n++;
            l++;
            if (l == mps * 5)  {    // report each 5 seconds
                LOG.info("Quotes sent: {}",  l);
                l = 0;
            }
            if (n == mps) {
                long t1 = System.currentTimeMillis();
                long td = t1 - t0;      // should be 1 second
                if (td < 1000)
                    Thread.sleep(1000 - td);
                t0 = System.currentTimeMillis();
                n = 0;
            }
        }
    }

}
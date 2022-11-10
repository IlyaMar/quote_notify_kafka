package org.imartynov.quote.input;

import org.imartynov.quote.input.initializers.IndexProperties;
import org.imartynov.quote.stock.dto.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);
    int counter = 0;
    long prevReportTime = 0;
    private IndexProperties properties;

    @Autowired
    public Consumer(IndexProperties properties) {
        this.properties = properties;
    }

    void report(Quote q, int partition) {
        counter++;
        if (prevReportTime == 0)
            prevReportTime = System.currentTimeMillis();
        long now = System.currentTimeMillis();
        if (now > prevReportTime + 5000) {      // report each 5 seconds
            LOG.info("Customer shard {}. Received {} {} {}", properties.getCustomerShardNumber(), q, partition, counter);
            counter = 0;
            prevReportTime = now;
        }
    }

    @KafkaListener(topics = {"stock_exchange"})
    public void consume(final @Payload Quote message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment) {
//        LOG.info("{} {} {}", offset, partition, message);
        report(message, partition);
        // TODO here search in customers watchlist
        acknowledgment.acknowledge();
    }

//    @RabbitListener(queues = ("stock_exchange"))
//    public void receiveProduct(Quote q) {
//        report(q);
//        // validatinon and filtration here ...
//        // send to fanout exchange
//        rabbitTemplate.convertAndSend(QuoteInputApplication.exchangeName, "", q);
//    }

}

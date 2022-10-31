package org.imartynov.quote.stock;

import org.imartynov.quote.stock.dto.Quote;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class Producer {

    private final KafkaTemplate<String, Quote> kafkaTemplate;

    public Producer(KafkaTemplate<String, Quote> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, Quote>> sendMessage(Quote q) {
        return this.kafkaTemplate.send(StockApplication.topicOut, null, q);
    }
}
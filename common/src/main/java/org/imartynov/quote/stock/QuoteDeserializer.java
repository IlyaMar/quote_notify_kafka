package org.imartynov.quote.stock;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.imartynov.quote.stock.dto.Quote;

import java.io.*;
import java.util.Map;

public class QuoteDeserializer implements Deserializer {

    public void configure(Map map, boolean b) {
    }

    @Override
    public Quote deserialize(String topic, byte[] data) {
        if (data == null)
            return null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(is);
            return (Quote) ois.readObject();
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Quote", e);
        }
    }

    public void close() {

    }
}
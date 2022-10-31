package org.imartynov.quote.stock;

import org.apache.kafka.common.errors.SerializationException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class QuoteSerializer implements org.apache.kafka.common.serialization.Serializer {

    public void configure(Map map, boolean b) {
    }

    public byte[] serialize(String s, Object o) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Error when serializing Quote to byte[]", e);
        }
    }

    public void close() {

    }
}
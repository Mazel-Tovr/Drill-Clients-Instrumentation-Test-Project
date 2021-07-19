package com.epam.kafka;

import org.apache.commons.lang3.*;
import org.apache.kafka.clients.producer.*;

import java.io.*;
import java.util.*;


public class BasicProducer {

    private static BasicProducer instance;

    public static BasicProducer getInstance() {
        if (instance == null) {
            synchronized (BasicProducer.class) {
                if (instance == null) {
                    instance = new BasicProducer();
                }
            }
        }
        return instance;
    }

    private final SimpleProducer<byte[], byte[]> producer;

    private static final String brokerList = "EPRUTVEW0077.moscow.epam.com:9092";
    private static final String topic = "topic";
    private static final String groupId = "my-group";
    private static final String client = "clientId";

    private BasicProducer() {
        Properties producerConfig = new Properties();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG, client);
        producerConfig.put("group.id", groupId);
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
        producerConfig.put(ProducerConfig.RETRIES_CONFIG, "3");
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");

        producer = new SimpleProducer<>(producerConfig, true);
    }

    public void sendToKafka(int clazz, int method) {
        producer.send(topic, serialize(clazz), serialize(method));
    }

    private static byte[] serialize(final Object obj) {
        return SerializationUtils.serialize((Serializable) obj);
    }

}

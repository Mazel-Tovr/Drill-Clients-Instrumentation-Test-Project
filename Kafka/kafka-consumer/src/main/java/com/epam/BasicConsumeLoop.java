package com.epam;

import com.epam.stub.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.header.*;
import org.slf4j.*;
import com.epam.serialization.*;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class BasicConsumeLoop<K extends Serializable, V extends Serializable> implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BasicConsumeLoop.class);

    private final KafkaConsumer<K, V> consumer;
    private final List<String> topics;
    private final String clientId;

    private CountDownLatch shutdownLatch = new CountDownLatch(1);

    public BasicConsumeLoop(Properties configs, List<String> topics) {
        this.clientId = configs.getProperty(ConsumerConfig.CLIENT_ID_CONFIG);
        this.topics = topics;
        this.consumer = new KafkaConsumer<>(configs);
    }

    @Override
    public void run() {

        try {
            logger.info("Starting the Consumer : {}", clientId);

            ConsumerRebalanceListener listener = new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    logger.info("C : {}, Revoked partitionns : {}", clientId, partitions);
                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    logger.info("C : {}, Assigned partitions : {}", clientId, partitions);
                }
            };
            consumer.subscribe(topics, listener);

            logger.info("C : {}, Started to process records", clientId);
            while (true) {
                ConsumerRecords<K, V> records = consumer.poll(Duration.ofSeconds(10));
//                java.util.Iterator records = records1.iterator();
//                while (records.hasNext()) {
//                    org.apache.kafka.clients.consumer.ConsumerRecord record = (org.apache.kafka.clients.consumer.ConsumerRecord) records.next();
//                    java.util.Iterator header = record.headers().headers("$kafkaHeaders").iterator();
//                    while (header.hasNext()) {
//                        org.apache.kafka.common.header.Header subHeader = (org.apache.kafka.common.header.Header) header.next();
//                    }
//                }

                if (records.isEmpty()) {
                    logger.info("C : {}, Found no records", clientId);
                    continue;
                }
                logger.info("C : {} Total No. of records received : {}", clientId, records.count());
                for (ConsumerRecord<K, V> record : records) {
                    Integer value = (Integer) record.value();
                    Integer key = (Integer) record.key();
                    logger.info("C : {}, Record received partition : {}, key : {}, value : {}, offset : {}",
                            clientId, record.partition(), key, value, record.offset());

                    switch (key) {
                        case 1:
                            switch (value) {
                                case 1:
                                    new Class1().method1();
                                    break;
                                case 2:
                                    new Class1().method2();
                                    break;
                                case 3:
                                    new Class1().method3();
                                    break;
                                default:
                                    System.out.println("IDK");
                            }
                            break;
                        case 2:
                            switch (value) {
                                case 1:
                                    new Class2().method1();
                                    break;
                                case 2:
                                    new Class2().method2();
                                    break;
                                case 3:
                                    new Class2().method3();
                                    break;
                                default:
                                    System.out.println("IDK");
                            }
                            break;
                        case 3:
                            switch (value) {
                                case 1:
                                    new Class3().method1();
                                    break;
                                case 2:
                                    new Class3().method2();
                                    break;
                                case 3:
                                    new Class3().method3();
                                    break;
                                default:
                                    System.out.println("IDK");
                            }
                            break;
                        default:
                            System.out.println("IDKX2");

                    }
                }
            }
        } catch (WakeupException e) {
            // Ignore, we're closing
        } finally {
            consumer.close();
            shutdownLatch.countDown();
            logger.info("C : {}, consumer exited", clientId);
        }
    }

    public void close() {
        try {
            consumer.wakeup();
            shutdownLatch.await();
        } catch (InterruptedException e) {
            logger.error("Error", e);
        }
    }

    public static void main(String[] args) {


        List<String> topics = Collections.singletonList(topic);

        final BasicConsumeLoop<Serializable, Serializable> consumer = new BasicConsumeLoop<>(getConsumerConfigs(), topics);
        consumer.run();

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));

    }

    static String brokerList = "EPRUTVEW0077.moscow.epam.com:9092";
    static String topic = "topic";
    static String groupId = "my-group";
    static String earliest = "earliest";
    static String client = "clientId";

    private static Properties getConsumerConfigs() {
        Properties configs = new Properties();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, earliest);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configs.put(ConsumerConfig.CLIENT_ID_CONFIG, client);
        configs.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "1024");

        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class.getName());
        return configs;
    }

}

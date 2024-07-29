package com.tutorial;

import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.tutorial.avro.User;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class AvroProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://localhost:8081");

        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(props);

        String topic = "test.avro";

        GenericRecord user = new GenericRecordBuilder(User.SCHEMA$)
                .set("name", "John Doe")
                .set("age", 30)
                .build();

        ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(topic, "key1", user);
        producer.send(record);
        producer.flush();
        producer.close();
    }
}
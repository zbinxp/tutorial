package com.tutorial;

import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tutorial.developer.Util;

public class KafkaStreamsApplication {
    private static final Logger logger = LoggerFactory.getLogger(KafkaStreamsApplication.class);

    static void runKafkaStreams(final KafkaStreams streams){
        final CountDownLatch latch = new CountDownLatch(1);
        streams.setStateListener((newState, oldState) -> {
            if(oldState == KafkaStreams.State.RUNNING && newState != KafkaStreams.State.RUNNING){
                latch.countDown();
            }
        });
        streams.start();
        try{
            latch.await();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        logger.info("Streams closed");
    }

    static Topology buildTopology(String inputTopic, String outputTopic){
        Serde<String> stringSerde = Serdes.String();
        StreamsBuilder builder = new StreamsBuilder();
        builder.stream(inputTopic, Consumed.with(stringSerde, stringSerde))
            .peek((k,v) -> logger.info("observed event:{}", v))
            .mapValues(s -> s.toUpperCase())
            .peek((k,v)-> logger.info("transformed event:{}", v))
            .to(outputTopic, Produced.with(stringSerde, stringSerde))
            ;
        return builder.build();
    }

    public static void main(String[] args) throws Exception{
        if(args.length < 1){
            throw new IllegalArgumentException("missing the path to a configuration file");
        }
        Properties props = new Properties();
        try(InputStream inputStream = new FileInputStream(args[0])){
            props.load(inputStream);
        }

        final String inputTopic = props.getProperty("input.topic.name");
        final String outputTopic = props.getProperty("output.topic.name");

        try(Util util = new Util()){
            util.createTopics(props, Arrays.asList(
                new NewTopic(inputTopic, Optional.empty(), Optional.empty()),
                new NewTopic(outputTopic, Optional.empty(), Optional.empty())
            ));

            try(Util.Randomizer rando = util.startNewRandomizer(props, inputTopic)){
                KafkaStreams kafkaStreams = new KafkaStreams(
                    buildTopology(inputTopic, outputTopic), 
                    props);
                Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

                logger.info("Kafka Streams App Started");
                runKafkaStreams(kafkaStreams);
            }
        }
    }
}

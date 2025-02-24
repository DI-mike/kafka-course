package com.honolulu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class LowLatencyProducer {
	private static final Logger log = LoggerFactory.getLogger(LowLatencyProducer.class);
	private static final String TOPIC = "latency";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://broker-1:9092,http://broker-2:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		props.put(ProducerConfig.ACKS_CONFIG, "0");
		props.put(ProducerConfig.LINGER_MS_CONFIG, "0");
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, "1024");

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);


		long i = 0;
		while(i < 10000) {
			ProducerRecord<String, String> producerRecord =
					new ProducerRecord<>(TOPIC, String.valueOf(i), String.valueOf(i));
			producer.send(producerRecord);
			i++;
		}

		producer.flush();
		producer.close();

		log.info("Successfully produced messages");

		}
}

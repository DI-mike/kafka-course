package com.honolulu;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class LowThroughputProducer {
	private static final Logger log = LoggerFactory.getLogger(LowThroughputProducer.class);
	private static final String TOPIC = "throughput";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://broker-1:9092,http://broker-2:9093");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(ProducerConfig.LINGER_MS_CONFIG, "0");
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, "1024");
		props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);

		long startTime = System.currentTimeMillis(); //fetch starting time
		long i = 0;
		while((System.currentTimeMillis() - startTime) < 10000) {
			ProducerRecord<String, String> producerRecord =
					new ProducerRecord<>(TOPIC, String.valueOf(i), String.valueOf(i));
			producer.send(producerRecord);
			i++;
		}


		producer.flush();
		producer.close();

		log.info("Successfully produced " + i + "messages in 10 seconds");

		}
}

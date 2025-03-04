package com.example.simplekafkaredisrdmbs.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViewCountProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "view-count";

	public void send(String contentId){
		kafkaTemplate.send(TOPIC, contentId);
	}
}

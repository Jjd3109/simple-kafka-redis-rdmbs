package com.example.simplekafkaredisrdmbs.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewCountProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private static final String TOPIC = "view-count";

	public void send(String contentId){
		try{
			kafkaTemplate.send(TOPIC, contentId);
		}catch(Exception e){
			log.info("에러 : {} ", e.getMessage());
		}

	}
}

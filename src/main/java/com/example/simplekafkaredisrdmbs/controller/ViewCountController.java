package com.example.simplekafkaredisrdmbs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplekafkaredisrdmbs.kafka.ViewCountProducer;
import com.example.simplekafkaredisrdmbs.redis.ViewCountConsumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ViewCountController {

	private final ViewCountProducer viewCountProducer;
	private final ViewCountConsumer viewCountConsumer;

	@PostMapping("/api/v1/{id}")
	public void viewCount(@PathVariable String id){
		viewCountProducer.send(id);
	}

	@GetMapping("/api/v1/{id}")
	public String read(@PathVariable String id){
		log.info("viewCountConsumer.getViewCount(id) 값 = {} ", viewCountConsumer.getViewCount(id));
		return "결과";
	}
}

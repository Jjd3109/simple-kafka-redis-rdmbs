package com.example.simplekafkaredisrdmbs.service;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.simplekafkaredisrdmbs.kafka.ViewCountProducer;
import com.example.simplekafkaredisrdmbs.redis.ViewCountConsumer;
import com.example.simplekafkaredisrdmbs.repository.ViewCountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ViewCountService {

	private final StringRedisTemplate redisTemplate;
	private final ViewCountRepository viewCountRepository;
	private static final String KEY_FORMAT = "view_count::view::";

	@Scheduled(fixedRate = 30000) // 30초마다 실행
	@Transactional
	public void syncViewCountsToDB() {
		Set<String> keys = redisTemplate.keys(KEY_FORMAT + "*");
		if (keys != null) {
			for (String key : keys) {
				String contentId = key.replace(KEY_FORMAT, "");
				Long count = Long.valueOf(redisTemplate.opsForValue().get(key));

				viewCountRepository.updateViewCount(Long.valueOf(contentId), count);

			}
		}
	}




}

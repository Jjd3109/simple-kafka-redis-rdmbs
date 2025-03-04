package com.example.simplekafkaredisrdmbs.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViewCountConsumer {

	private final StringRedisTemplate redisTemplate;
	private static final String KEY_FORMAT = "view_count::view::%s";

	@KafkaListener(topics = "view-count" , groupId = "view-count-group")
	public void processViewCount(String contentId){
		redisTemplate.opsForValue().increment(KEY_FORMAT.formatted(contentId)); // contentId값을 증가
	}

	/*
	 * 단일 건 조히
	 */
	public Long getViewCount(String contentId) {
		String key = KEY_FORMAT.formatted(contentId);
		return redisTemplate.opsForValue().get(key) != null ? Long.parseLong(redisTemplate.opsForValue().get(key)) : 0;
	}

}

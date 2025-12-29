package com.t3h.projectclothes.service.impl;

import com.t3h.projectclothes.service.RateLimitingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RateLimitingServiceImpl implements RateLimitingService {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public boolean isAllowed(String key, long limit, long durationInSecond) {
    return false;
  }
}

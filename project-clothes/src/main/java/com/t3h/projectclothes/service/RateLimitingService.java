package com.t3h.projectclothes.service;

public interface RateLimitingService {

  boolean isAllowed(String key, long limit, long durationInSecond);
}

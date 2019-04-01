package com.fhs.ucenter.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "producer", fallback = PropertyService.class)
public interface PropertyService {
}

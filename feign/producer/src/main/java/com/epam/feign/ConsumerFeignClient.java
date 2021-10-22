package com.epam.feign;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "feign-consumer", url = "${consumer.url}:${consumer.port}")
@RequestMapping("/api")
public interface ConsumerFeignClient {

    @GetMapping("/feign/1")
    String firstController();

    @GetMapping("/feign/2")
    String secondController();

}

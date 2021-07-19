package com.epam.rest;

import com.epam.kafka.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/message")
    public String sendToKafka(@RequestParam("class") Integer clazz, @RequestParam("method") Integer method) {
        BasicProducer basicProducer = BasicProducer.getInstance();
        basicProducer.sendToKafka(clazz > 4 ? 3 : clazz , method > 4 ? 3 : method);
        return "{ \"status\": \"200\", \"message\":\"" + method + "\"}";
    }

}

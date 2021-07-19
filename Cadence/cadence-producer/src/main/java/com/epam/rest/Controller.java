package com.epam.rest;

import com.epam.cadence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    Invoker invoker;

    @GetMapping("/message")
    public String sendToCadence(@RequestParam("class") Integer clazz, @RequestParam("method") Integer method) {
        invoker.invoke(clazz > 4 ? 3 : clazz , method > 4 ? 3 : method);
        return "{ \"status\": \"200\", \"message\":\"class: " + clazz + " method: " + method + "\"}";
    }

}

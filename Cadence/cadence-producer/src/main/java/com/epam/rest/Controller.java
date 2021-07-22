package com.epam.rest;

import com.epam.cadence.async.*;
import com.epam.cadence.sync.*;
import com.uber.cadence.workflow.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Invoker invoker;
    @Autowired
    private InvokerAsync invokerAsync;

    @GetMapping("/message")
    public String sendToCadence(@RequestParam("class") Integer clazz, @RequestParam("method") Integer method) {
        invoker.invoke(clazz > 4 ? 3 : clazz, method > 4 ? 3 : method);
        return "{ \"status\": \"200\", \"message\":\"class: " + clazz + " method: " + method + "\"}";
    }

    @GetMapping("/async/message")
    public String sendAsyncToCadence(@RequestParam("class") Integer clazz, @RequestParam("method") Integer method) {
        return "{ \"status\": \"200\", \"message\":\"" + invokerAsync.invokeAsync(clazz, method) + "\"}";
    }

}

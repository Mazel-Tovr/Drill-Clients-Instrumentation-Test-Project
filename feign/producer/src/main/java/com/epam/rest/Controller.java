package com.epam.rest;

import com.epam.feign.*;
import com.epam.utli.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ConsumerFeignClient feignClient;

    @GetMapping("/feign")
    public String sendToAnotherService(@RequestParam("controller") Integer controllerId) {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());
        String response;
        switch (controllerId){
            case 1:
                response = feignClient.firstController();
                break;
            case 2:
                response = feignClient.secondController();
                break;
            default:
                response = "Unknown service";
        }

        return "{ \"status\": \"200\", \"message\":\"controllerId\": \"" + controllerId + "\" \"response\": \"" + response + "\"}";
    }

}

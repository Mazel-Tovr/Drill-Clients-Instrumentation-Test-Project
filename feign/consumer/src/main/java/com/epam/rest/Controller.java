package com.epam.rest;

import com.epam.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {


    @GetMapping("/feign/1")
    public String firstController() {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());

        return "{ \"status\": \"200\", \"Headers\": \" "+ RequestHelper.getHeaderFromCurrentRequest() +"\"}";
    }

    @GetMapping("/feign/2")
    public String secondController() {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());

        return "{ \"status\": \"200\", \"Headers\": \" "+ RequestHelper.getHeaderFromCurrentRequest() +"\"}";
    }

}

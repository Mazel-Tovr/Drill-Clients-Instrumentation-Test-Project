package com.epam.rest;

import com.epam.stub.*;
import com.epam.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Class1 class1;
    @Autowired
    private Class2 class2;


    @GetMapping("/feign/1")
    public String firstController() {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());
        class1.method1();
        class1.method2();
        return "{ \"status\": \"200\", \"Headers\": \" " + RequestHelper.getHeaderFromCurrentRequest() + "\"}";
    }

    @GetMapping("/feign/2")
    public String secondController() {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());
        class2.method1();
        class2.method2();
        return "{ \"status\": \"200\", \"Headers\": \" " + RequestHelper.getHeaderFromCurrentRequest() + "\"}";
    }

}

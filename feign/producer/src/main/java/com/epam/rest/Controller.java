package com.epam.rest;

import com.epam.dto.*;
import com.epam.feign.*;
import com.epam.utli.*;
import com.google.gson.*;
import okhttp3.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ConsumerFeignClient feignClient;

    @GetMapping("/feign")
    public String sendToAnotherService(@RequestParam("controller") Integer controllerId) {
        System.out.println("Request with headers income: " + RequestHelper.getHeaderFromCurrentRequest());
        String response = switch (controllerId) { // Java 17 feature
            case 1 -> feignClient.firstController();
            case 2 -> feignClient.secondController();
            default -> "Unknown service";
        };
        System.out.println("Sum : " + new SimpleDTO(1, 2).sum());
        return """
                { "status": "200", "message":"controllerId": "%s" "response": "%s"}
                 """.formatted(controllerId, response); // Java 17 feature
    }


    public String OK_NE_OK() {
        try {
            Response response = new OkHttpClient().newCall(new Request.Builder().url("http://postman-echo.com/headers").build()).execute();
            Object headers = new Gson().fromJson(new InputStreamReader(response.body().byteStream()), Map.class).get("headers");
            System.out.println(headers);
            return headers.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }

}

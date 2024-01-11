package com.consumeapi.consumeapi.controller;

import com.consumeapi.consumeapi.domain.CreditCard;
import com.consumeapi.consumeapi.domain.CreditCardList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {


    @GetMapping("health")
    public String consumeApi()
    {
        return "OK";
    }
    @GetMapping("consume")
    public ResponseEntity<String> consumeApi(@RequestParam final String URL)
    {
        RestTemplate template= new RestTemplate();
        String responseEntity=template.getForObject(URL, String.class);
        return new ResponseEntity<String>(responseEntity, HttpStatus.OK);
    }
}

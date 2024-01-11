package com.consumeapi.consumeapi.controller;

import com.consumeapi.consumeapi.domain.CreditCard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@RestController
public class ConsumerController {


    @GetMapping("health")
    public String consumeApi()
    {
        return "OK";
    }
    @GetMapping("consume")
    public ResponseEntity<List<CreditCard>> consumeApi(@RequestParam final String URL)
    {
        RestTemplate template= new RestTemplate();
        String responseEntity=template.getForObject(URL, String.class);
        Gson gson = new Gson();
        JsonObject jSon = new Gson().fromJson(responseEntity, JsonObject.class);
        String creditCardJsonArray=jSon.get("data").toString();

        Type listType = new TypeToken<List<CreditCard>>() {}.getType();
        List<CreditCard> creditCardList = gson.fromJson(creditCardJsonArray, listType);
        return new ResponseEntity<List<CreditCard>>(creditCardList, HttpStatus.OK);
    }
}

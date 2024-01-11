package com.consumeapi.consumeapi.controller;

import com.consumeapi.consumeapi.domain.CreditCard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ConsumerController {


    @GetMapping("health")
    public String consumeApi()
    {
        return "OK";
    }
    private final String DATE_FORMAT="MM/YY";

    private final String BASE_URL="https://fakerapi.it/api/v1/credit_cards?_quantity=";

    @GetMapping("consume")
    public ResponseEntity<List<CreditCard>> consumeApi(@RequestParam final String thresholdDate,@RequestParam final String quantity)
    {

        Date recievedThresholdDate=null;
        Integer numberOfRecords=null;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        if(StringUtils.isNumeric(quantity))
        {
            numberOfRecords=Integer.parseInt(quantity);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        try {
            recievedThresholdDate=sdf.parse(thresholdDate);

        } catch (ParseException e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        RestTemplate template= new RestTemplate();
        String responseEntity=template.getForObject(BASE_URL+quantity, String.class);
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();
        JsonObject jSon = new Gson().fromJson(responseEntity, JsonObject.class);
        String creditCardJsonArray=jSon.get("data").toString();

        Type listType = new TypeToken<List<CreditCard>>() {}.getType();
        List<CreditCard> creditCardList = gson.fromJson(creditCardJsonArray, listType);
        Date finalRecievedThresholdDate = recievedThresholdDate;
        creditCardList=creditCardList.stream().filter(creditCard -> {
                return creditCard.getExpiration().after(finalRecievedThresholdDate);
        }).toList();
        return new ResponseEntity<>(creditCardList, HttpStatus.OK);
    }


}

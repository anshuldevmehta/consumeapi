package com.consumeapi.consumeapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreditCardList
{
    private List<CreditCard> creditCardList;

    public CreditCardList() {
        creditCardList = new ArrayList<>();
    }
}

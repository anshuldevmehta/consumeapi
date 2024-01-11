package com.consumeapi.consumeapi.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditCard
{
    String type;
    String number;
    String expiration;
    String owner;
}

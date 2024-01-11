package com.consumeapi.consumeapi.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CreditCard
{
    String type;
    String number;
    Date expiration;
    String owner;
}

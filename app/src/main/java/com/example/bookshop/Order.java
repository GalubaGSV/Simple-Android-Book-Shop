package com.example.bookshop;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Order {
    String userName;
    String goodsName;
    int quantity;
    double orderPrice;
}

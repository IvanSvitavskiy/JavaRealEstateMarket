package com.example.RealEstateMarket.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Building {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String desc;
    @Getter
    @Setter
    private int price;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String owner;
}

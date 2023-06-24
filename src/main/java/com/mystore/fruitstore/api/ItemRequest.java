package com.mystore.fruitstore.api;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemRequest {

    private String name;
    private String description;
    private List<PropertyRequest<?>> properties;

}

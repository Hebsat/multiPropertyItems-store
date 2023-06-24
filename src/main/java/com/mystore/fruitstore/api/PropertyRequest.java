package com.mystore.fruitstore.api;

import lombok.*;

@Getter
@Setter
@ToString
public class PropertyRequest<T> {

    private String name;
    private T value;
}

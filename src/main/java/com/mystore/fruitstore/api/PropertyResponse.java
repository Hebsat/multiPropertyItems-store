package com.mystore.fruitstore.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(value = NON_NULL)
public class PropertyResponse<T> {

    private Integer id;
    private String name;
    private T value;
}

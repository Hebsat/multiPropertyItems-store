package com.mystore.fruitstore.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@JsonInclude(value = NON_NULL)
public class ItemResponse {

    private Integer id;
    private String name;
    private String description;
    private Double cost;
    private List<PropertyResponse<?>> properties;
}

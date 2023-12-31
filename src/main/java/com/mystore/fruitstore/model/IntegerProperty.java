package com.mystore.fruitstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "integer_properties")
public class IntegerProperty extends AbstractProperty {

    @Column(name = "integer_value", nullable = false)
    private Integer integerValue;
}

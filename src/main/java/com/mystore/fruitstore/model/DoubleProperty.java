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
@Table(name = "double_properties")
public class DoubleProperty extends AbstractProperty {

    @Column(name = "double_value", nullable = false)
    private Double doubleValue;
}

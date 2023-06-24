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
@Table(name = "string_properties")
public class StringProperty extends AbstractProperty {

    @Column(name = "string_value", nullable = false)
    private String stringValue;
}

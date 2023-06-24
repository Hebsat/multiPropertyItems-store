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
@Table(name = "boolean_properties")
public class BooleanProperty extends AbstractProperty {

    @Column(name = "boolean_value", nullable = false)
    private Boolean booleanValue;
}

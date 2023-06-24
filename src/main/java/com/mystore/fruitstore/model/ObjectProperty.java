package com.mystore.fruitstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "object_properties")
public class ObjectProperty extends AbstractProperty {

    @ManyToOne
    @JoinColumn(name = "item_value", nullable = false)
    private Item itemValue;
}

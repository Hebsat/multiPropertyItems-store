package com.mystore.fruitstore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "property_name", nullable = false)
    private String propertyName;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}

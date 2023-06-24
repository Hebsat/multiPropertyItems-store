package com.mystore.fruitstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "item_name", nullable = false)
    private String itemName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "item")
    private List<AbstractProperty> properties;
}

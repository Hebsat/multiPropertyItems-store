package com.mystore.fruitstore.repositories;

import com.mystore.fruitstore.model.AbstractProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<AbstractProperty, Integer> {
}

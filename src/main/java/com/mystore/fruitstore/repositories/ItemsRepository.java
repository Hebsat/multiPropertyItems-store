package com.mystore.fruitstore.repositories;

import com.mystore.fruitstore.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
}

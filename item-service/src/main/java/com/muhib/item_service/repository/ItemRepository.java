package com.muhib.item_service.repository;

import com.muhib.item_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNameContainingIgnoreCase(String name);
}

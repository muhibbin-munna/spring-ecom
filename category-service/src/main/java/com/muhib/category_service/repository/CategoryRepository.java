package com.muhib.category_service.repository;


import com.muhib.category_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByCategoryNameContainingIgnoreCase(String name);

}

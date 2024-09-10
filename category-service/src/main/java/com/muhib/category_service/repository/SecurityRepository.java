package com.muhib.category_service.repository;

import com.muhib.category_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

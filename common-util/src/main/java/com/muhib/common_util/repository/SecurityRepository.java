package com.muhib.common_util.repository;

import com.muhib.common_util.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

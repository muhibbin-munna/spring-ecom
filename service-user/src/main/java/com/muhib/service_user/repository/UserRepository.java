package com.muhib.service_user.repository;

import com.muhib.service_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}

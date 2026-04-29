package com.lee.stayhub.repository;

import com.lee.stayhub.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByUsername(String username);


    boolean existsByUsername(String username);
}

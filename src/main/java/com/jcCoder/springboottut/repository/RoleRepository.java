package com.jcCoder.springboottut.repository;

import com.jcCoder.springboottut.entity.UserEntity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}

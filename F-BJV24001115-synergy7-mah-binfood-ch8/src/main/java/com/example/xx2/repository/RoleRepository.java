package com.example.xx2.repository;

import com.example.xx2.model.ERole;
import com.example.xx2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(ERole roleUser);
}

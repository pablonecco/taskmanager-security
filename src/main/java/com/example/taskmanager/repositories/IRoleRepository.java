package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("roleRepository")
public interface IRoleRepository extends JpaRepository<RoleEntity, Serializable> {
    public abstract RoleEntity findById (int id);
}

package com.example.taskmanager.repositories;

import com.example.taskmanager.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<UserEntity, Serializable> {

    public abstract UserEntity findByUsername(@Param("username")String username);

    //@Query("SELECT u FROM UserEntity u JOIN FETCH u.roles WHERE u.username = (:username)")
    //public abstract UserEntity findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

}

package com.example.demo.repository;

import com.example.demo.model.entity.User;
import com.example.demo.interfaces.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(excerptProjection = UserSummary.class)
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where ( u.name IS NULL OR u.name LIKE :name%)")
    List<User> findByNameContaining(@Param("name") String name);



}

package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.User;

@Repository // It is optional to write when we are using JPA
public interface UserRepository extends JpaRepository<User, Long> {

}

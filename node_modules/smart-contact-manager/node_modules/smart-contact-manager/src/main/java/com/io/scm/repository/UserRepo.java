package com.io.scm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.scm.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);

    public Optional<User> findByEmailAndPassword(String email, String password);

    public Optional<User> findByEmailToken(String emailToken);

}

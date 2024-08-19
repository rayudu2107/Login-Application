package com.jeev.login_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeev.login_application.model.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}

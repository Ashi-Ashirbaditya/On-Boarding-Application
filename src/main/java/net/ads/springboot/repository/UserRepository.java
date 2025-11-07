package net.ads.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.ads.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

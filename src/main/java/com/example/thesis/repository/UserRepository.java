package com.example.thesis.repository;

import com.example.thesis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1 AND user_id <> ?2",nativeQuery = true)
    Optional<User> findByEmailAndUserIdNot(String email,Long userId);
    List<User> findAllByEmailIn(List<String> emails);

}

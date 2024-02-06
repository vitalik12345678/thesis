package com.example.thesis.repository;

import com.example.thesis.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<UserToken,Long> {

    List<UserToken> findAllByEmailIn(List<String> emails);

    Optional<UserToken> findByEmail(String email);
}

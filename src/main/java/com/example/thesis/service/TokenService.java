package com.example.thesis.service;

import com.example.thesis.entity.UserToken;

import java.util.List;
import java.util.Optional;

public interface TokenService extends CRUDService<UserToken,Long> {
    List<UserToken> saveAll(List<UserToken> userTokens);

    Optional<UserToken> findByTokenAndEmailOpt(String token, String email);

    void deleteByToken(String token);
}

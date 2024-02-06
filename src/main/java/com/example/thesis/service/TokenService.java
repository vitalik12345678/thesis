package com.example.thesis.service;

import com.example.thesis.entity.UserToken;

import java.util.List;

public interface TokenService extends CRUDService<UserToken,Long> {
    List<UserToken> saveAll(List<UserToken> userTokens);
}

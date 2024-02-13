package com.example.thesis.service.impl;

import com.example.thesis.entity.UserToken;
import com.example.thesis.exception.ExistException;
import com.example.thesis.repository.TokenRepository;
import com.example.thesis.service.TokenService;
import com.example.thesis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl extends CRUDServiceImpl<UserToken,Long> implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;


    @Override
    protected JpaRepository<UserToken, Long> getRepository() {
        return tokenRepository;
    }

    @Override
    @Transactional
    public List<UserToken> saveAll(List<UserToken> userTokens) {
        userTokens.removeIf( item -> userService.findByEmailOpt(item.getEmail()).isPresent());
        var emailCount = userTokens.stream().map(UserToken::getEmail).count();
        if ( userTokens.size() != emailCount) {
            throw new ExistException(" Check your csv on duplicate emails ");
        }

        userTokens.forEach(item -> tokenRepository.findByEmail(item.getEmail()).ifPresentOrElse(exist ->  {
            exist.setToken(item.getToken());
            tokenRepository.save(exist);
        }
        , () -> tokenRepository.save(item)) );

        return tokenRepository.findAllByEmailIn(userTokens.stream().map(UserToken::getToken).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserToken> findByTokenAndEmailOpt(String token, String email) {
        return tokenRepository.findByTokenAndEmail(token,email);
    }

    @Override
    @Transactional
    public void deleteByToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}

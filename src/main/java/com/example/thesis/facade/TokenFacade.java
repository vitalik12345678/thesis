package com.example.thesis.facade;

import com.example.thesis.dto.EmailCsvCreationDTO;
import com.example.thesis.dto.TokenCreationDTO;
import com.example.thesis.entity.UserToken;
import com.example.thesis.exception.ValidationException;
import com.example.thesis.factory.TokenFactory;
import com.example.thesis.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;
    private final TokenFactory tokenFactory;

    @Transactional
    public List<TokenCreationDTO> createAll(List<EmailCsvCreationDTO> emailDetailsList) {
        var userTokens = tokenFactory.toUserTokenList(emailDetailsList);

        var unvalidToken = userTokens.stream().filter( item -> !item.getEmail().contains("@")).findFirst();
        if (unvalidToken.isPresent()) {
            throw new ValidationException( "You wrote wrong email %s".formatted( unvalidToken.get().getEmail()));
        }

        var savedEntities = tokenService.saveAll(userTokens);

        return tokenFactory.toTokenCreationDTOList(savedEntities);
    }

    @Transactional(readOnly = true)
    public Optional<UserToken> findByTokenAndEmailOpt(String token,String email) {
        return tokenService.findByTokenAndEmailOpt(token,email);
    }

    @Transactional
    public void deleteByToken(String token) {
        tokenService.deleteByToken(token);
    }
}

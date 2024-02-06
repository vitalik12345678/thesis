package com.example.thesis.factory;

import com.example.thesis.dto.EmailCsvCreationDTO;
import com.example.thesis.dto.TokenCreationDTO;
import com.example.thesis.entity.UserToken;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenFactory {

    private final EntityMapper entityMapper;

    public List<UserToken> toUserTokenList(List<EmailCsvCreationDTO> csvCreationDTOS) {
        return entityMapper.toUserTokenList(csvCreationDTOS);
    }

    public UserToken toUserToken(EmailCsvCreationDTO csvCreationDTO) {
        return entityMapper.toUserToken(csvCreationDTO);
    }

    public List<TokenCreationDTO> toTokenCreationDTOList(List<UserToken> userTokens) {
        return entityMapper.toTokenCreationDTOList(userTokens);
    }

    public TokenCreationDTO toTokenCreationDTO(UserToken userToken) {
        return entityMapper.toTokenCreationDTO(userToken);
    }
}

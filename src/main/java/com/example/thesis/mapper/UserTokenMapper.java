package com.example.thesis.mapper;

import com.example.thesis.dto.EmailCsvCreationDTO;
import com.example.thesis.dto.TokenCreationDTO;
import com.example.thesis.entity.UserToken;

import java.util.List;

public interface UserTokenMapper {
    List<UserToken> toUserTokenList(List<EmailCsvCreationDTO> csvCreationDTOS);

    UserToken toUserToken(EmailCsvCreationDTO csvCreationDTO);

    List<TokenCreationDTO> toTokenCreationDTOList(List<UserToken> userTokens);

    TokenCreationDTO toTokenCreationDTO(UserToken userToken);
}

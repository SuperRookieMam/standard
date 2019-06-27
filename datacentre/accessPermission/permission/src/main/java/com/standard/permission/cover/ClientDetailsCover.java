package com.standard.permission.cover;

import com.standard.oauthCommon.dto.ClientDetailsDto;
import com.standard.permission.entity.OauthClientDetails;

public class ClientDetailsCover {

    public static ClientDetailsDto toDto(OauthClientDetails clientDetails ){
        ClientDetailsDto clientDetailsDto =  new ClientDetailsDto();
        clientDetailsDto.setClientId(clientDetails.getClientId());
        clientDetailsDto.setResourceIds(clientDetails.getResourceIds());
        clientDetailsDto.setSecretRequired(clientDetails.isSecretRequired());
        clientDetailsDto.setClientSecret(clientDetails.getClientSecret());
        clientDetailsDto.setScope(clientDetails.getScope());
        clientDetailsDto.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes());
        clientDetailsDto.setRegisteredRedirectUri(clientDetails.getRegisteredRedirectUri());
        clientDetailsDto.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        clientDetailsDto.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
        clientDetailsDto.setAutoApprove(clientDetails.isAutoApprove());
        clientDetailsDto.setAdditionalInformation(clientDetails.getAdditionalInformation());
        clientDetailsDto.setAuthorities(clientDetails.getAuthorities());
        return clientDetailsDto;
    }

}

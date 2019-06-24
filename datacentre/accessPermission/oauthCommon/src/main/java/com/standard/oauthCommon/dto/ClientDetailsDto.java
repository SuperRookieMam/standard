package com.standard.oauthCommon.dto;

import com.standard.oauthCommon.entity.MClientDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class ClientDetailsDto implements Serializable,ClientDetails {
    private static final long serialVersionUID = -8556272908312313690L;

    private  String clientId;

    private  Set<String> resourceIds;

    private boolean secretRequired;

    private String clientSecret;

    private  Set<String> scope;

    private  Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    private Map<String, Object> additionalInformation;

    private Collection<GrantedAuthority> authorities;

    public static<T extends MClientDetails> ClientDetailsDto toDto(T clientDetails ){
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
        clientDetailsDto.setAutoApprove(clientDetails.isAutoApprove(""));
        clientDetailsDto.setAdditionalInformation(clientDetails.getAdditionalInformation());
        clientDetailsDto.setAuthorities(clientDetails.getAuthorities());
        return clientDetailsDto;
    }

    @Override
    public boolean isScoped() {
        return true;
    }
    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }
}

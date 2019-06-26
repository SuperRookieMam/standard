package com.standard.oauthCommon.dto;

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

    @Override
    public boolean isScoped() {
        return true;
    }
    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }
}

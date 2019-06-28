package com.standard.oauthCommon.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class ClientDetailsDto implements Serializable,ClientDetails {
    private static final long serialVersionUID = -8556272908312313690L;

    private  String clientId;

    private  Set<String> resourceIds=new HashSet<>();

    private boolean secretRequired;

    private String clientSecret;

    private  Set<String> scope=new HashSet<>();

    private  Set<String> authorizedGrantTypes=new HashSet<>();

    private Set<String> registeredRedirectUri=new HashSet<>();

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private boolean autoApprove;

    private Map<String, Object> additionalInformation =new HashMap<>();

    private Collection<GrantedAuthority> authorities =new ArrayList<>();

    @Override
    public boolean isScoped() {
        return true;
    }
    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }
}

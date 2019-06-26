package com.standard.oauthCommon.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;
@Getter
@Setter
public class GrantedAuthorityDto implements GrantedAuthority {
    private static final long serialVersionUID = -4885160400306369284L;

    private Long id;

    private String resourceId;

    private String apiName;

    private String apiDescription;

    private String apiUri;

    private HttpMethod method;

    private String mactherType;

    private String roleId;

    private String roleName;

    private String scope;

    @Override
    public String getAuthority() {
        return JSONObject.toJSONString(this);
    }
}

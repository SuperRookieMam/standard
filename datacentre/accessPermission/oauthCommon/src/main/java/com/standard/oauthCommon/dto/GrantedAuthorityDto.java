package com.standard.oauthCommon.dto;

import com.alibaba.fastjson.JSONObject;
import com.standard.oauthCommon.entity.MGrantedAuthority;
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

    public static<T extends MGrantedAuthority>  GrantedAuthorityDto toDto(T grantedAuthority){
        GrantedAuthorityDto grantedAuthorityDto =new GrantedAuthorityDto();
        grantedAuthorityDto.setId(grantedAuthority.getId());
        grantedAuthorityDto.setResourceId(grantedAuthority.getResourceId());
        grantedAuthorityDto.setApiName(grantedAuthority.getApiName());
        grantedAuthorityDto.setApiDescription(grantedAuthority.getApiDescription());
        grantedAuthorityDto.setApiUri(grantedAuthority.getApiUri());
        grantedAuthorityDto.setMethod(grantedAuthority.getMethod());
        grantedAuthorityDto.setMactherType(grantedAuthority.getMactherType());
        grantedAuthorityDto.setRoleId(grantedAuthority.getRoleId());
        grantedAuthorityDto.setRoleName(grantedAuthority.getRoleName());
        grantedAuthorityDto.setScope(grantedAuthority.getScope());
        return grantedAuthorityDto;
    }
    @Override
    public String getAuthority() {
        return JSONObject.toJSONString(this);
    }
}

package com.standard.securityCommon.access;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;

@Getter
@Setter
public class RequestAuthorityAttribute implements ConfigAttribute {

    private static final long serialVersionUID = 5861389645003319286L;

    private String apiUri;

    private HttpMethod method;

    private String mactherType;

    private String roleId;

    private String roleName;

    private String scope;

    private boolean accessVisit =false;

    public RequestAuthorityAttribute() {
        super();
    }

    public RequestAuthorityAttribute(String apiUri,
                                     HttpMethod method,
                                     String mactherType,
                                     String roleId,
                                     String roleName,
                                     String scope) {
        super();
        this.apiUri = apiUri;
        this.method = method;
        this.mactherType  = mactherType;
        this.roleId =roleId;
        this.roleName =roleName;
        this.scope =scope;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiUri == null) ? 0 : apiUri.hashCode());
        result = prime * result + ((method == null) ? 0 : method.hashCode());
        result = prime * result + ((mactherType == null) ? 0 : mactherType.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RequestAuthorityAttribute other = (RequestAuthorityAttribute) obj;
        if (apiUri == null) {
            if (other.apiUri != null)
                return false;
        } else if (!apiUri.equals(other.apiUri))
            return false;
        if (method != other.method)
            return false;

        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.apiUri))
            return false;

        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        return true;
    }

    @Override
    public String getAttribute() {
        return JSONObject.toJSONString(this);
    }
}

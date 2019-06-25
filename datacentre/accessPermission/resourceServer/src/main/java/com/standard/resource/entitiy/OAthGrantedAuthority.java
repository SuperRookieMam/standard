package com.standard.resource.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import com.standard.oauthCommon.entity.MGrantedAuthority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IsCreate
@Table(name = "oath_granted_authority_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"resource_id_", "role_info", "api_uri"})},
        indexes = {@Index(columnList = "client_id")
                , @Index(columnList = "role_info")})
public class OAthGrantedAuthority extends BaseEntity implements MGrantedAuthority {
    private static final long serialVersionUID = 4062924753193768577L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "resource_id_")
    private String resourceId;

    /*apiName*/
    @Column(name = "api_name_")
    private String apiName;

    /*api描述*/
    @Column(name = "api_description_")
    private String apiDescription;

    /*资源定位*/
    @Column(name = "api_uri_")
    private String apiUri;

    //对此接口的读写权限,如果多个
    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private HttpMethod method = HttpMethod.GET;

    @Column(name = "macther_type_")
    private String mactherType;

    @Column(name = "role_id_")
    private String roleId;

    @Column(name = "role_name_")
    private String roleName;

    // 定义某几个参数的规则，
    @Column(name = "scope_")
    private String scope;

    @Override
    public String getAuthority() {
        return "";
    }
}

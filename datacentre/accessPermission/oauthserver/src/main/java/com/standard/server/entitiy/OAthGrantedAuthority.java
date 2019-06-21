package com.standard.server.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.oauthCommon.entity.MGrantedAuthority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "oath_granted_authority_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id", "role_info", "api_uri"})},
        indexes = {@Index(columnList = "client_id")
                , @Index(columnList = "role_info")})
public class OAthGrantedAuthority extends BaseEntity implements MGrantedAuthority {
    private static final long serialVersionUID = 4062924753193768577L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //客户端id
    @Column(name = "client_id")
    private String clientId;

    /*apiName*/
    @Column(name = "api_name")
    private String apiName;
    /*api描述*/
    @Column(name = "api_description")
    private String apiDescription;

    /*资源定位*/
    @Column(name = "api_uri")
    private String apiUri;

    //对此接口的读写权限,如果多个
    @Column(name = "method")
    @Enumerated(EnumType.STRING)
    private HttpMethod method = HttpMethod.GET;

    @Column(name = "macther_type")
    private String mactherType;

    @Override
    public String getAuthority() {
        return null;
    }
}

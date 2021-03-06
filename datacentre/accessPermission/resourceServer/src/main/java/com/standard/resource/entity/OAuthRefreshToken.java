package com.standard.resource.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;

/**
 * 在项目中,主要操作oauth_refresh_token表的对象是JdbcTokenStore.java. \
 * (与操作oauth_access_token表的对象一样);更多的细节请参考该类. 
 * 如果客户端的grant_type不支持refresh_token,则不会使用该表.
 */
@Getter
@Setter
@Entity
@IsCreate
@Table(name = "oauth_refresh_token_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"token_id_"})},
        indexes= {@Index(columnList = "token_id_")})
public class OAuthRefreshToken extends BaseEntity {
    private static final long serialVersionUID = 9080251217485358480L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;
    /**
     * 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    @Column(name = "token_id_")
    private String tokenId;

    /**
     * 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    @Lob
    @Column(name = "authentication_")
    private OAuth2Authentication authentication;


}

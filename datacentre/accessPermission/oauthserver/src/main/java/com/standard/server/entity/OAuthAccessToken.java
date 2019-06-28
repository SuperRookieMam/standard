package com.standard.server.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 在项目中,主要操作oauth_access_token表的对象是JdbcTokenStore.java. 更多的细节请参考该类.
 */
@Getter
@Setter
@Entity
@IsCreate
@Table(name = "oauth_access_token_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"authentication_id_"})},
        indexes = {@Index(columnList = "authentication_id_")})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OAuthAccessToken extends BaseEntity {
    private static final long serialVersionUID = -4432050622441349608L;
    /**
     * 该字段的值是将access_token的值通过MD5加密后存储的.
     */
    @Id
    @Column(name = "token_id_")
    @GeneratedValue(generator = "jpa-uuid")
    private String tokenId;
    /**
     * accessToken序列化后的base64字符
     * */
    @Lob
    @Column(name = "token_")
    private String token;
    /**
     * 该字段具有唯一性, 其值是根据当前的username(如果有),
     * client_id与scope通过MD5加密生成的.
     * 具体实现请参考DefaultAuthenticationKeyGenerator.java类.
     */
    @Column(name = "authentication_id_")
    private String authenticationId;
    /**
     * 存储将OAuth2Authentication.java对象序列化后的二进制数据.
     */
    @Lob
    @Column(name = "authentication_")
    private String authentication;
    /**
     *
     * */
    @Column(name = "client_id_")
    private String clientId;
    /**
     * 该字段的值是将refresh_token的值通过MD5加密后存储的.
     */
    @Lob
    @Column(name = "refresh_token_")
    private String refreshToken;

    @Column(name = "token_type_")
    private String tokenType;

    /*@Column(name = "scope_")*/
    @Transient
    private Set<String> scope;

    @Column(name = "expiration_")
    private Date expiration;
    /**
     * 登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),
     * 则该值等于client_id
     */
    @Column(name = "user_name_")
    private String userName;

    @Transient
    private Map<String,Object> additionalInformation;


}

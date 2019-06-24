package com.standard.resource.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@IsCreate
@Table(name = "authorized_grant_type_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id_", "grant_type_"})},
        indexes= {@Index(columnList = "client_id_")})
public class AuthorizedGrantType extends BaseEntity {

    private static final long serialVersionUID = 4388749422719856987L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "client_id_", nullable = false, length = 32)
    private String clientId;

//    其中用Grant Type代表当前授权的类型。 Grant Type包括：
//    authorization_code：传统的授权码模式
//    implicit：隐式授权模式
//    password：资源所有者（即用户）密码模式
//    client_credentials：客户端凭据（客户端ID以及Key）模式
//    refresh_token：获取access token时附带的用于刷新新的token模式
    @Column(name = "grant_type_", nullable = false, length = 32)
    private String grantType;
}

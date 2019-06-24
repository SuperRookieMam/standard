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
@Table(name = "client_registered_redirect_uri_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id_", "redirect_uri_"})},
        indexes= {@Index(columnList = "client_id_")})
public class ClientRegisteredRedirectUri extends BaseEntity {

    private static final long serialVersionUID = 5659395845747258201L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "client_id_", nullable = false)
    private String clientId;

    @Column(name = "redirect_uri_", nullable = false)
    private String redirectUri;
}

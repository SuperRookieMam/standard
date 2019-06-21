package com.standard.server.entitiy;

import com.standard.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client_scope_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id","uri_","scope"})},
        indexes = {@Index(columnList = "client_id")})
public class ClientScope extends BaseEntity {
    private static final long serialVersionUID = 6264195683179623743L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id_")
    private String clientId;

    @Column(name = "scope_")
    private String scope;

    @Column(name = "uri_")
    private String uri;

}

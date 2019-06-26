package com.standard.resource.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IsCreate
@Table(name = "client_scope_")
public class ClientScope extends BaseEntity {
    private static final long serialVersionUID = 9084222588280252484L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;


    @Column(name = "client_id_")
    private String clientId;

    @Column(name = "resource_id_")
    private String resourceId;

    @Column(name = "scope_")
    private String scope;
}

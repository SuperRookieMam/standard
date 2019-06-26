package com.standard.resource.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@IsCreate
@Table(name = "user_role_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name_"})},
        indexes = {@Index(columnList = "user_name_")})
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -526335157679328935L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "user_name_")
    private String userName;

    @Column(name = "role_id_")
    private Long roleId;



}

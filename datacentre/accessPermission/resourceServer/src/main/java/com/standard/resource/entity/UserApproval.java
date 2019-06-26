package com.standard.resource.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.oauth2.provider.approval.Approval;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@IsCreate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_aproval_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id_", "client_id_", "scope_"})},
        indexes = {@Index(columnList = "user_id_")})
public class UserApproval extends BaseEntity {

    private static final long serialVersionUID = 5194945293219450500L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "user_id_", length = 50)
    private String userId;


    @Column(name = "client_id_", length = 50)
    private String clientId;


    @Column(name = "scope_", length = 100)
    private String scope;

    @Column(name = "status_")
    @Enumerated(EnumType.STRING)
    private Approval.ApprovalStatus status;

    @Column(name = "expires_at_")
    private Date expiresAt;

    @Column(name = "last_update_at_")
    private Date lastUpdatedAt = new Date();

}

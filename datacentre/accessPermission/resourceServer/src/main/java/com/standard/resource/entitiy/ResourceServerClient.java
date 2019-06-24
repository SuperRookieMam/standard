package com.standard.resource.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@IsCreate
@Getter
@Setter
@Table(name = "resource_server_client_",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"client_id_", "resource_id_"})},
        indexes= {@Index(columnList = "client_id_")})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ResourceServerClient extends BaseEntity {
    private static final long serialVersionUID = 8181194360392195940L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id_",length =32)
    private String id;

    // 客户端Id
    @Column(name = "client_id_")
    private String clientId;

    @Column(name = "resource_id_")
    private String resourceId;


}

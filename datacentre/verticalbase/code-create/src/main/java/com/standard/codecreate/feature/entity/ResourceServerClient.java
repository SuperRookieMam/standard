package com.standard.codecreate.feature.entity;

import com.standard.codecreate.feature.annotation.Description;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@IsCreate
public class ResourceServerClient  {
    private static final long serialVersionUID = 8181194360392195940L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    // 客户端Id
    @Column(name = "client_id")
@Description
    private String clientId;

    @Column(name = "resource_id")
@Description
    private Long resourceId;

    @Column(name = "company_id")
@Description
    private Long companyId;


}

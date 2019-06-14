package com.standard.codecreate.feature.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ResourceServerClient  {
    private static final long serialVersionUID = 8181194360392195940L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 客户端Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "company_id")
    private Long companyId;


}

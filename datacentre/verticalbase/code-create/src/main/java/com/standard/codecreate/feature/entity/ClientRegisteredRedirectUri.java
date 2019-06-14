package com.standard.codecreate.feature.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ClientRegisteredRedirectUri  {

    private static final long serialVersionUID = 5659395845747258201L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id", nullable = false, length = 50)
    private String clientId;

    @Column(name = "redirect_uri", nullable = false, length = 50)
    private String redirectUri;

    @Column(name = "company_id")
    private Long companyId;
}

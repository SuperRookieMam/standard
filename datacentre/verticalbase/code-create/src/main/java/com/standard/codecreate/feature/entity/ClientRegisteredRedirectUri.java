package com.standard.codecreate.feature.entity;

import com.standard.codecreate.feature.annotation.Description;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@IsCreate
@Entity
public class ClientRegisteredRedirectUri  {
    private static final long serialVersionUID = 5659395845747258201L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    @Description
    private Long id;

    @Column(name = "client_id", nullable = false, length = 50)
    @Description
    private String clientId;

    @Column(name = "redirect_uri", nullable = false, length = 50)
    @Description
    private String redirectUri;

    @Column(name = "company_id")
    @Description
    private Long companyId;
}

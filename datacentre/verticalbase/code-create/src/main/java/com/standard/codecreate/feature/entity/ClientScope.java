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
public class ClientScope  {
    private static final long serialVersionUID = 6264195683179623743L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    @Column(name = "client_id")
@Description
    private String clientId;

    @Column(name = "scope")
@Description
    private String scope;

    @Column(name = "company_id")
@Description
    private Long companyId;

}

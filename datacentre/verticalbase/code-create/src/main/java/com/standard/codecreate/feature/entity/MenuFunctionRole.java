package com.standard.codecreate.feature.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MenuFunctionRole  {

    private static final long serialVersionUID = 757205122408504955L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "menu_function_id")
    private Long menuFunctionId;


    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "company_id")
    private Long companyId;

}

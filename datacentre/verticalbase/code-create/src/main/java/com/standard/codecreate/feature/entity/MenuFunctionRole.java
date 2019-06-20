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
public class MenuFunctionRole  {

@Description
    private static final long serialVersionUID = 757205122408504955L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    @Column(name = "menu_function_id")
@Description
    private Long menuFunctionId;


    @Column(name = "role_id")
@Description
    private Long roleId;

    @Column(name = "company_id")
@Description
    private Long companyId;

}

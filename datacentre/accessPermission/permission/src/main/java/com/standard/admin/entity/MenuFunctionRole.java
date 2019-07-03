package com.standard.admin.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import com.standard.permission.componet.constpackage.ConstParam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IsCreate
@Table(name = "menu_function_role_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_function_id_", "role_id_", "resource_id_"})})
public class MenuFunctionRole extends BaseEntity {
    private static final long serialVersionUID = 3561453495316361656L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "menu_function_id_")
    private Long menuFunctionId;


    @Column(name = "role_id_")
    private Long roleId;

    @Column(name = "resource_id_")
    private String resourceId = ConstParam.RESOURCE_ID;
}

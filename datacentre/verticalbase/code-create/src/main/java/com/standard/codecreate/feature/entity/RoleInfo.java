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
public class RoleInfo  {
    private static final long serialVersionUID = -6133439565976081362L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    // 角色名称
    @Column(name = "role_name")
@Description
    private String rolName;

    //公司Id
    @Column(name = "company_id")
@Description
    private Long companyId;
    //公司Id
    @Column(name = "company_name")
@Description
    private String companyName;

    //部门//预留
    @Column(name = "department_id")
@Description
    private Long departmentId;
    //部门Id//预留
    @Column(name = "department_name")
@Description
    private String departmentName;

}

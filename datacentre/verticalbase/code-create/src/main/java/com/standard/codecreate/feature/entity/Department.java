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
public class Department  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    //公司名字
    @Column(name = "department_name")
@Description
    private String departmentName;
    // 营业执照
    @Column(name = "company_name")
@Description
    private String companyName;
    // 父级Id
    @Column(name = "pid")
@Description
    private String pid;

    // 部门负责人id
    @Column(name = "principal_man")
@Description
    private String principalMan;

    @Column(name = "company_id")
@Description
    private Long companyId;
}

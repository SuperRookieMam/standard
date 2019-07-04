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
@Table(name = "menu_function_",
              uniqueConstraints = {
        @UniqueConstraint(columnNames = {"function_number_", "resource_id_"})})
public class MenuFunction extends BaseEntity {
    private static final long serialVersionUID = -7318256292767023491L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "cname_")
    private String cname;
    //属于那个组
    @Column(name = "ename_")
    private String ename;

    @Column(name = "url_")
    private String url;

    @Column(name = "sort_")
    private Integer sort;

    @Column(name = "function_number_")
    private Integer functionNumber;

    @Column(name = "pid_")
    private Integer pid;

    @Column(name = "is_menu_")
    private Integer isMenu;

    @Column(name = "is_show_")
    private Integer isShow;

    @Column(name = "is_flow_")
    private Integer isFlow;

    @Column(name = "resource_id_")
    private String resourceId = ConstParam.RESOURCE_ID;

}

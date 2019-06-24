package com.standard.server.entitiy;

import com.standard.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "role_info_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_name_"})})
public class RoleInfo extends BaseEntity {
    private static final long serialVersionUID = -6133439565976081362L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 角色名称
    @Column(name = "role_name_")
    private String rolname;

    //公司Id
    @Column(name = "pid_")
    private Long pid;



}

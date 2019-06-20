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
public class ResourceServer  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;
    /**
     * 资源服务器名字
     */
    @Column(name = "name")
@Description
    private String name;
    /**
     * 注册地址
     */
    @Column(name = "register_url")
@Description
    private String registerUrl;
    /**
     * 说明用来存储的是资源服务器的凭证
     */
    @Column(name = "remark")
@Description
    private String remark;
    /**
     * 是否使用
     */
    @Column(name = "is_use")
@Description
    private Integer isUse = 1;

    @Column(name = "company_id")
@Description
    private Long companyId;
}

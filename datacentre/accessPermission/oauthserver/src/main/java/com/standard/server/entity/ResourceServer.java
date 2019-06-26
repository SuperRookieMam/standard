package com.standard.server.entity;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IsCreate
@Table(name = "resource_server_")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ResourceServer extends BaseEntity {
    private static final long serialVersionUID = 6572448256141191118L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id_")
    private String id;
    /**
     * 资源服务器名字
     */
    @Column(name = "name_")
    private String name;
    /**
     * 注册地址
     */
    @Column(name = "register_url_")
    private String registerUrl;
    /**
     * 说明用来存储的是资源服务器的凭证
     */
    @Column(name = "secret_")
    private String secret;
    /**
     * 是否使用
     */
    @Column(name = "use_")
    private boolean use = true;
}

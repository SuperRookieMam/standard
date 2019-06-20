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
public class MenuFunction  {
@Description
    private static final long serialVersionUID = -53761947833746331L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")

    @Description(label = "id",search = true,isColumn = true,searchType = "text")
    private Long id;

    @Column(name = "cname_")

    @Description(label = "中文名称",search = true,isColumn = true,searchType = "text")
    private String cname;
    //属于那个组
    @Column(name = "ename_")
    @Description(label = "英文名",search = true,isColumn = true,searchType = "text")
    private String ename;

    @Column(name = "url_")
    @Description(label = "跳转地址",search = true,isColumn = true,searchType = "text")
    private String url;

    @Column(name = "sort_")
    @Description(label = "排序",search = true,isColumn = true,searchType = "text")
    private Integer sort;

    @Column(name = "function_number")
    @Description(label = "功能编号",search = true,isColumn = true,searchType = "text")
    private Integer functionNumber;

    @Column(name = "pid_")
    @Description(label = "父ID",search = true,isColumn = true,searchType = "text")
    private Integer pid;

    @Column(name = "is_menu_")
    @Description(label = "中文名称",search = true,isColumn = true,searchType = "text")
    private Integer isMenu;

    @Column(name = "is_show")
@Description
    private Integer isShow;

    @Column(name = "is_flow")
@Description
    private Integer isFlow;

    @Column(name = "company_id")
    @Description(label = "公司编号",search = true,isColumn = true,searchType = "text")
    private Long companyId;

}


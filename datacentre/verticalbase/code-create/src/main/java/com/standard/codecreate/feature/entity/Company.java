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
public class Company  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
@Description
    private Long id;

    //公司名字
    @Column(name = "company_name")
@Description
    private String companyName;

    //公司秘密
    @Column(name = "secret")
@Description
    private String secret;

    // 营业执照
    @Column(name = "business_license")
@Description
    private String businessLicense;

    //组织机构代码
    @Column(name = "organization_code")
@Description
    private String organizationCode;


    //营业执照图片地址
    @Column(name = "business_license_image")
@Description
    private String businessLicenseImage;

    //法人代表id
    @Column(name = "legal_person_id")
@Description
    private String legalPersonId;
}

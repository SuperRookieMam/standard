package com.standard.permission.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.permission.entity.AuthorizedGrantType;
import com.standard.permission.service.AuthorizedGrantTypeService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizedGrantTypeServiceImpl extends BaseServiceImpl<AuthorizedGrantType, Long> implements AuthorizedGrantTypeService {
}

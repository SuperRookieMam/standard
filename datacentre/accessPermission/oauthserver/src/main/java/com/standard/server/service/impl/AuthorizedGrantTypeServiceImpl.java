package com.standard.server.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.server.entity.AuthorizedGrantType;
import com.standard.server.service.AuthorizedGrantTypeService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizedGrantTypeServiceImpl extends BaseServiceImpl<AuthorizedGrantType, Long> implements AuthorizedGrantTypeService {
}

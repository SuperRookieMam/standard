package com.standard.permission.controller;

import com.standard.base.componet.dto.ResultDto;
import com.standard.permission.service.OAuthAccessTokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Test")
public class LoginController {
    @Autowired
    private OAuthAccessTokenService oAuthAccessTokenService;
    @GetMapping
    @ResponseBody
    @ApiOperation(value="登陆", notes="logIn")
    public ResultDto login(){
        return ResultDto.success("允许登陆");
    }

}

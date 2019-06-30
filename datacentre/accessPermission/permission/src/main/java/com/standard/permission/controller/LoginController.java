package com.standard.permission.controller;

import com.alibaba.fastjson.JSONObject;
import com.standard.base.componet.dto.ResultDto;
import com.standard.oauthCommon.dto.UserDetailsDto;
import com.standard.permission.componet.annotation.CurrentUser;
import com.standard.permission.entity.OAthUserDetailes;
import com.standard.permission.entity.OAuthAccessToken;
import com.standard.permission.service.OAuthAccessTokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("Test")
public class LoginController {
    @Autowired
    private OAuthAccessTokenService oAuthAccessTokenService;
    @GetMapping
    @ResponseBody
    @ApiOperation(value="登陆", notes="logIn")
    public ResultDto login(@CurrentUser UserDetailsDto oAthUserDetailes){
        OAuthAccessToken oAuthAccessToken =new OAuthAccessToken();
        oAuthAccessToken. setClientId("5555555555555");
        oAuthAccessToken.setExpiration(new Date());
        oAuthAccessToken.setTokenType("");
        oAuthAccessTokenService.insertByEntity(oAuthAccessToken);
        return ResultDto.success(JSONObject.toJSONString(oAthUserDetailes));
    }

}

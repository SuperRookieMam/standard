package com.standard.permission.controller;

import com.standard.base.componet.dto.ResultDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("logIn")
public class LoginController {

    @GetMapping
    @ResponseBody
    @ApiOperation(value="登陆", notes="logIn")
    public ResultDto login(){
        return ResultDto.success("允许登陆");
    }

}

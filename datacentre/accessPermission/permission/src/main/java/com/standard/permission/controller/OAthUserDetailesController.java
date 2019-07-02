package com.standard.permission.controller;

import com.standard.base.componet.dto.ResultDto;
import com.standard.base.controller.BaseController;
import com.standard.oauthCommon.dto.UserDetailsDto;
import com.standard.permission.entity.OAthUserDetailes;
import com.standard.securityCommon.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userDetailes")
public class OAthUserDetailesController extends BaseController<OAthUserDetailes,Long> {

    @GetMapping("userInfo")
    @ResponseBody
    @ApiOperation(value="根据Id查询实体", notes="findById")
    public ResultDto userInfo(@CurrentUser UserDetailsDto userDetailsDto){
        return ResultDto.success(userDetailsDto);
    }
}

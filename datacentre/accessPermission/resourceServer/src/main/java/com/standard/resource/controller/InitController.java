package com.standard.resource.controller;

import com.standard.resource.componet.init.InitTable;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("init")
public class InitController {

    @Autowired
    InitTable initTable;


    @GetMapping("1")
    @ResponseBody
    @ApiOperation(value="初始化数据", notes="insertByEntity")
    public  String getAccessToken() throws Exception {
        initTable.init();
        return "初始化完毕";
    }

}

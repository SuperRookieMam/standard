package com.standard.base.controller;

import com.standard.base.componet.dto.ResultDto;
import com.standard.base.componet.util.ParamUtil;
import com.standard.base.entity.BaseEntity;
import com.standard.base.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.Map;

public class BaseController<T extends BaseEntity,ID extends Serializable> {

    @Autowired
    BaseService<T,ID> baseService;

    @GetMapping("{id}")
    @ResponseBody
    @ApiOperation(value="根据Id查询实体", notes="findById")
    public ResultDto findById(@PathVariable("id") ID id){
        return ResultDto.success(baseService.findById(id));
    }

    @GetMapping(value = "list",params = {"params"})
    @ResponseBody
    @ApiOperation(value="根据参数查询", notes="findByParams")
    public ResultDto findByParams(@RequestParam Map<String,String> map  ){
        return ResultDto.success(baseService.findListByParams(ParamUtil.mapToDynamicParam(map)));
    }
    @GetMapping(value = "page",params = {"params"})
    @ResponseBody
    @ApiOperation(value="根据参数查询", notes="findPageParams")
    public ResultDto findPageParams(@RequestParam Map<String,String> map  ){
        return ResultDto.success(baseService.findpageByParams(ParamUtil.mapToDynamicParam(map)));
    }





}

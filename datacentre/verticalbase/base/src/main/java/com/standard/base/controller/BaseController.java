package com.standard.base.controller;

import com.standard.base.componet.dto.ResultDto;
import com.standard.base.componet.params.DynamicParam;
import com.standard.base.componet.util.ParamUtil;
import com.standard.base.entity.BaseEntity;
import com.standard.base.service.BaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public class BaseController<T extends BaseEntity,ID extends Serializable> {

    @Autowired
    BaseService<T,ID> baseService;

    @GetMapping("{id}")
    @ResponseBody
    @ApiOperation(value="根据Id查询实体", notes="findById")
    public ResultDto findById(@PathVariable("id") ID id){
        return ResultDto.success(baseService.findById(id));
    }

    @GetMapping(value = "list")
    @ResponseBody
    @ApiOperation(value="根据参数查询", notes="findByParams")
    public ResultDto findByParams(@RequestParam("dynameicParams") String dynamicParams){
        return ResultDto.success(baseService.findListByParams(ParamUtil.strToDynamicParam(dynamicParams)));
    }
    @GetMapping(value = "page")
    @ResponseBody
    @ApiOperation(value="根据参数查询", notes="findPageParams")
    public ResultDto findPageParams(@RequestParam("dynameicParams") String dynamicParams){
        return ResultDto.success(baseService.findPageByParams(ParamUtil.strToDynamicParam(dynamicParams)));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value="根据实体列表跟新", notes="updateByEntitys")
    public ResultDto updateByEntitys(@RequestBody List<T> entitys){
        return ResultDto.success(baseService.updateByEntitys(entitys));
    }

    @PostMapping(value ="params")
    @ResponseBody
    @ApiOperation(value="根据条件跟新", notes="updateByParams")
    public ResultDto updateByParams(@RequestBody DynamicParam dynamicParam){
        return ResultDto.success(baseService.updateByParams(dynamicParam));
    }
    @PutMapping
    @ResponseBody
    @ApiOperation(value="根据列表插入", notes="insertByEntitys")
    public ResultDto insertByEntitys(@RequestBody List<T> entitys){
        return ResultDto.success(baseService.insertByEntitys(entitys));
    }
    @DeleteMapping("{id}")
    @ResponseBody
    @ApiOperation(value="根据ID删除", notes="deletByIds")
    public ResultDto deletByIds(@PathVariable ID id){
        baseService.deletById(id);
        return ResultDto.success(1);
    }
    @DeleteMapping(value = "params")
    @ResponseBody
    @ApiOperation(value="根据ID删除", notes="deletByIds")
    public ResultDto deletByParams(@RequestParam("dynameicParams") String dynamicParams){
        return ResultDto.success(baseService.deletByParam(ParamUtil.strToDynamicParam(dynamicParams)));
    }


}

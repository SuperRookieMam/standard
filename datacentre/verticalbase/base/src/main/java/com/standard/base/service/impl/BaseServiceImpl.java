package com.standard.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.standard.base.componet.dto.ResultDto;
import com.standard.base.componet.params.DynamicParam;
import com.standard.base.componet.util.ParamUtil;
import com.standard.base.dao.BaseRepository;
import com.standard.base.entity.BaseEntity;
import com.standard.base.service.BaseService;
import com.standard.orm.componet.feature.*;
import com.standard.orm.componet.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BaseServiceImpl<T extends BaseEntity,ID extends Serializable> implements BaseService<T, ID> {
    @Autowired
    BaseRepository<T,ID> baseRepository;
    @Override
    public T  findById(ID id) {
        return baseRepository.findById(id).get();
    }
    @Override
    public List<T> findByIds(Iterable<ID> iterable){
        return  baseRepository.findAllById(iterable);
    }
    @Override
    public List<T> findListByParams(DynamicParam dynamicParam){
        DynamicTypeSelect<T> dynamicTypeSelect = baseRepository.getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele ->{
            Predicate predicate = ParamUtil.analysisDynamicParam(dynamicParam,ele.builder,ele.root);
            if (!ObjectUtils.isEmpty(predicate))
                ele.query.where(predicate);
           JSONArray jsonArray = dynamicParam.getSort();
           ParamUtil.orderby(ele.builder,ele.query,ele.root,jsonArray.toArray(new String[0]));
           ParamUtil.groupBy(ele.query,ele.root,dynamicParam.getGroupby().toArray(new String[0]));
            return predicate;
        });
        return  dynamicTypeSelect.getResult();
    }
    @Override
    public PageInfo<T> findpageByParams(DynamicParam dynamicParam){
        Assert.isTrue(!ObjectUtils.isEmpty(dynamicParam.getPageNum())&&!ObjectUtils.isEmpty(dynamicParam.getPageSize()),"分页信息不能为空");
        DynamicPageTypeSelect<T> DynamicPageTypeSelect = baseRepository.readPageType(dynamicParam.getPageNum(),dynamicParam.getPageSize());
        DynamicPageTypeSelect.dynamicBuild(ele ->{
            Predicate predicate = ParamUtil.analysisDynamicParam(dynamicParam,ele.builder,ele.root);
            Predicate predicateCount = ParamUtil.analysisDynamicParam(dynamicParam,ele.builder,ele.countRoot);
            if (!ObjectUtils.isEmpty(predicate))
                ele.query.where(predicate);
                ele.countQuery.where(predicateCount);
            JSONArray jsonArray = dynamicParam.getSort();
            ParamUtil.orderby(ele.builder,ele.query,ele.root,jsonArray.toArray(new String[0]));
            ParamUtil.groupBy(ele.query,ele.root,dynamicParam.getGroupby().toArray(new String[0]));
            ParamUtil.groupBy(ele.countQuery,ele.countRoot,dynamicParam.getGroupby().toArray(new String[0]));
            return predicate;
        });
        return  DynamicPageTypeSelect.getResult();
    }
    @Override
    public T updateByEntity(T entity){
       return baseRepository.save(entity);
    }
    @Override
    public List<T> updateByEntirys(Iterable<T> iterable){
       return baseRepository.saveAll(iterable);
    }
    @Override
    public int updateByParams(DynamicParam dynamicParam){
        DynamicUpdate<T> dynamicUpdate = baseRepository.getDynamicUpdate();
        JSONObject jsonObject = dynamicParam.getUpdateFiled();
        Assert.isTrue(!ObjectUtils.isEmpty(jsonObject),"更新字段不能为空");
        dynamicUpdate.dynamicBuild(ele ->{
            Set<String> set=jsonObject.keySet();
            Iterator<String> iterator=set.iterator();
            while (iterator.hasNext()){
                String key =iterator.next();
                Path path =PathUtil.getPath(ele.root,key, JoinType.INNER);
                Object object =FlatBuilder.getComparable(path,jsonObject.get(key));
                ele.update.set(path,object);
            }
            Predicate predicate = ParamUtil.analysisDynamicParam(dynamicParam,ele.builder,ele.root);
            ele.update.where(predicate);
            return null;
        });
        return dynamicUpdate.getResult();
    }

    @Override
    public void deletById(ID id){
        baseRepository.deleteById(id);
    }

    @Override
    public void deletByEntitys(Iterable<T> entitys){
        baseRepository.deleteAll(entitys);
    }
    @Override
    public int deletByParam(DynamicParam dynamicParam){
        DynamicDelete<T> dynamicDelete = baseRepository.getDynamicDelete();
        dynamicDelete.dynamicBuild(ele -> {
        Predicate predicate = ParamUtil.analysisDynamicParam(dynamicParam,ele.builder,ele.root);
            ele.delete.where(predicate);
            return predicate;
        });
        return dynamicDelete.getResult();
    }

}

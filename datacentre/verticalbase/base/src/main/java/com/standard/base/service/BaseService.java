package com.standard.base.service;

import com.standard.base.componet.dto.ResultDto;
import com.standard.base.componet.params.DynamicParam;
import com.standard.base.entity.BaseEntity;
import com.standard.orm.componet.feature.PageInfo;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T extends BaseEntity,ID extends Serializable> {

    T findById(ID id);

    List<T> findByIds(Iterable<ID> iterable);

    List<T> findListByParams(DynamicParam dynamicParam);

    PageInfo<T> findpageByParams(DynamicParam dynamicParam);

    T updateByEntity(T entity);

    List<T> updateByEntirys(Iterable<T> iterable);

    int updateByParams(DynamicParam dynamicParam);

    void deletById(ID id);

    void deletByEntitys(Iterable<T> entitys);

    int deletByParam(DynamicParam dynamicParam);
}

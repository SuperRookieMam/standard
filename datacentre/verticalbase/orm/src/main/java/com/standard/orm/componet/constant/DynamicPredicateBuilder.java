package com.standard.orm.componet.constant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

public class DynamicPredicateBuilder<T> {
    private final CriteriaBuilder builder ;
    private final Root<T> root;

    public DynamicPredicateBuilder(CriteriaBuilder builder,Root<T> root){
        this.builder =builder;
        this.root =root;
    }
    /**
     * 创建一个扁平的条件构建工具
     * */
    public FlatBuilder<T> flatCondition(){
        return new FlatBuilder<>(builder,root);
    }

}

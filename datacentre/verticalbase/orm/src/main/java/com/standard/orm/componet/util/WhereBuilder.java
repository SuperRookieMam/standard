package com.standard.orm.componet.util;

import com.standard.orm.dao.BaseDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 此类是编写业务代码的时候快速构建查询条件,而设计的类
 * 基本上可以实现SQL的大部分工能，并实现了连式编程，详情参见方法说明
 * 此类已经注入到dao并且提供get方法获取
 * 使用发如下：
 *  WhereBuilder w = dao.getWhereBuilder();
 *   PredicateBuilder p= w.getPredicateBuilder();
 *   list<T> list =  w.where(
 *          p.addEq("name","zhasga").and().addLike("pass","ss").end(),
 *          p.addEq("sex","nv").or().addLike("name","tt").end(),
 *          )
 *          .orderby(" fieldName:desc"," fieldName:asc"...)
 *          .groupBy( fieldName"," fieldName"...)
 *          .buildTypeQuery()
 *          .resultlist()
 *  还有更多的组合，详情等待你研究下列组合方法，更多的功能你可以组合
 *  i belive you can
 * */
public class WhereBuilder<T,ID extends Serializable> {

    private EntityManager entityManager;

    private Class<T> clazz;

    private PredicateBuilder<T,ID> predicateBuilder;

    private CriteriaBuilder builder ;

    private CriteriaQuery<T> query ;

    private Root<T> root;

    private  Predicate currentpredicate;

    public WhereBuilder(BaseDao<T,ID> baseDao){
        this.entityManager = baseDao.getEntityManager();
        this.clazz = baseDao.getEntityClass();
        this.builder =this.entityManager.getCriteriaBuilder();
        this.query = this.builder.createQuery(clazz);
        this.root =this.query.from(clazz);
    }
    /**
     * and连接一个或者多个，你也可以一次构建多个，或者连式编程
     * */
    public WhereBuilder<T,ID> and(Predicate...predicates){
        currentpredicate = ObjectUtils.isEmpty(currentpredicate)
                ?builder.and(predicates)
                :builder.and(currentpredicate,builder.and(predicates));
        return this;
    }
    public WhereBuilder<T,ID> or(Predicate...predicates){
        currentpredicate = ObjectUtils.isEmpty(currentpredicate)
                ?builder.or(predicates)
                :builder.or(currentpredicate,builder.and(predicates));
        return this;
    }
    /**
     * 当你的条件构建完成过后表示你已经构建完成或返回一个你构建的查询条件
     * */
    public Predicate end(){
        Predicate predicate = currentpredicate;
        currentpredicate =null;
        return predicate;
    }
    /**
     * 返回一个predicateBuilder 也就是predicate的构建工具
     * */
    public PredicateBuilder<T,ID> getPredicateBuilder(){
        if (ObjectUtils.isEmpty(predicateBuilder)){
            predicateBuilder =new PredicateBuilder(builder,root);
        }
        return predicateBuilder;
    }
    /**/
    public WhereBuilder<T,ID> where(Predicate ...predicates){
        this.query.where(predicates);
        return this;
    }

    public WhereBuilder<T,ID>  groupBy (String ...gorupbys){
        if (gorupbys!=null){
            Path[] paths =new  Path [gorupbys.length];
            for (int i = 0; i < gorupbys.length ; i++) {
                String[] panthNames= gorupbys[i].split("\\.");
                Path path=null;
                for (int j = 0; j < panthNames.length; j++) {
                    path =j==0?root.get(panthNames[j]):path.get(panthNames[j]);
                }
                paths[i] = path;
            }
            this.query.groupBy(paths);
        }
        return this;
    }


    /**
     *获取排序
     * sort 的格式为 如果有多个排序字段为" fieldName:desc"," fieldName:asc"
     * */
    public WhereBuilder<T,ID> orderby(String... sorts){
        if (sorts!=null){
            List<Sort.Order> list =new ArrayList<>();
            for (int i = 0; i <sorts.length ; i++) {
                String[] sort =sorts[i].split(":");
                Sort.Direction direction=  "desc".equalsIgnoreCase(sort[1]) ?Sort.Direction.DESC:Sort.Direction.ASC;
                list.add(new Sort.Order(direction,sort[0]));
            }
            query.orderBy(QueryUtils.toOrders(new Sort(list), root, builder));
        }
        return this;
    }
    public TypedQuery<T> buildTypeQuery(){
        return   this.entityManager.createQuery(query);
    }



}

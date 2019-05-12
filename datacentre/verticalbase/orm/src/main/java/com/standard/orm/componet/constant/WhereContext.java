package com.standard.orm.componet.constant;

import com.alibaba.fastjson.JSONArray;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * 为实现参数实现不同条件的复杂的查询，
 * 自定义的以套参数结构,
 * 为实现模板接口可以通过参数，实现比较复杂的查询
 * */
@Getter
@Setter
public class WhereContext implements Serializable {
    private static final long serialVersionUID = 2999006637841047561L;
     /**
      * pageNum ： 如果需要分页的时候找个必传
      *             "当前页"
      * */
    private  Integer pageNum;
    /**
     * pageSize ： 如果需要分页的时候找个必传
     *            分页大小
     * */
    private Integer pageSize;
    /**
     *   sort :[{'sortType': 'desc','fieldName':'fieldName'}]
     *   如果此字段不为空，会跟你你传的字段信息来排序，不需要排序可以忽略
     * */
    private JSONArray sort;
    /**
     *   groupby :["fieldName1","fieldName2","fieldName3"]
     *  不为空时，会按照你的分组条件分组，不需要忽略此字段
     * */
    private JSONArray groupby;
    /**
     * 当需要查询或者跟新条件时会根据此字段表达式查询或者跟新
     * expressions 此字段分为树形结构，可以实现复杂的查询
     * 此字段主要用于前端JS模板构建的查询条件，以满足参数控制复杂查询
     * 此字段实现主要是递归实现，详情参见 Expression说明
     * */
    private ArrayList<Expression> expressions;
    /**
     * 当你只需要跟新表的一个字段或者几个字段的时候，
     * 或者是根据expressions跟新满足条件的表数据的某几个字段时，你需要这个参数
     * FieldContext 实际上为一个jsonobject 的封装,只是为了连式变成，封装一个Addfile方法，
     * 详情参见 FieldContext
     * */
    private FieldContext fieldContext;

}

package com.standard.orm.componet.constant;


import com.alibaba.fastjson.JSONObject;
/**
 * 其实就是一个JSONObject
 * 只是为了可以连式变成，增加一个方法
 * 若你要跟新某个实体的某几个字段的话可以如下
 * fieldContext.addField("name","张三")
 *             .addField("password","1234")
 * */
public class FieldContext extends JSONObject {

    public FieldContext addField(String fieldName, Object value){
        this.put(fieldName,value);
      return  this;
    }
}

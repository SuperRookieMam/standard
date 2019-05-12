package com.standard.orm.componet.util;

import com.alibaba.fastjson.JSONArray;
import com.standard.orm.componet.constant.FieldContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * 通过参数实现更新某个实体的某几个字段时，
 * 需要调用这个方法，实现这个类的自己个字段的设置
 * */
public class CopyFieldUtil {
    public static<T> void setFiled(FieldContext fieldContext, CriteriaUpdate<T> criteriaUpdate, EntityManager entityManager,Root<T> root){
        //获取要跟新的所有字段
        Iterator iterator = fieldContext.keySet().iterator();
        try {
            while (iterator.hasNext()){
                String key =iterator.next().toString();
                Assert.isTrue(!key.contains("."),"不支连表跟新");
                Path path = getPath(key,root);
                Class  cla =path.getJavaType();
                Object object = fieldContext.get(key);
                //如果为空证明是java系统类
                if (cla.getClassLoader() ==null){
                    if (List.class.isAssignableFrom(cla)){//一对多的情况
                         Class clazz = MyClassUtil.getSuperClassGenricType(cla,0);
                         criteriaUpdate.set(path,JSONArray.parseArray(fieldContext.getString(key),clazz));
                    }else if (Set.class.isAssignableFrom(cla)){//一对多的情况
                        Class clazz = MyClassUtil.getSuperClassGenricType(cla,0);
                        HashSet<?> hashSet =new HashSet();
                        hashSet.addAll(JSONArray.parseArray(fieldContext.getString(key),clazz));
                        criteriaUpdate.set(path,hashSet);
                    }else if (Map.class.isAssignableFrom(cla)){//map 映射 太复杂不做处理

                    }else {//基础类或者基础类的包装
                        criteriaUpdate.set(path,object);
                    }
                }else {// 多对一,一对一的情况
                    Object obj =  entityManager.getReference(path.getJavaType(),object);
                    criteriaUpdate.set(path,obj);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Path getPath(String key,Root root){
        Assert.notNull(key, "key值不能为空");
        Path path =null;
        if (key.contains(".")) {
            String[] keys = StringUtils.split(key, ".");
            path = root.get(keys[0]);
            Class clazz = path.getJavaType();
            if (Set.class.isAssignableFrom(clazz)) {
                SetJoin setJoin = root.joinSet(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    path = i==1?setJoin.get(keys[i]):path.get(keys[i]);
                }
            } else if (List.class.isAssignableFrom(clazz)) {
                ListJoin listJoin = root.joinList(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    path = i==1?listJoin.get(keys[i]):path.get(keys[i]);
                }
            } else if (Map.class.isAssignableFrom(clazz)) {
                MapJoin mapJoin = root.joinMap(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    path= i==1?mapJoin.get(keys[i]):path.get(keys[i]);
                }
            } else {
                //是many to one时
                for (int i = 1; i <keys.length ; i++) {
                    path = path.get(keys[i]);
                }
            }
        }else {//基本类型
            path = root.get(key);
        }
        return  path;
    }

}

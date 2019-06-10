package com.standard.orm.componet.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.Iterator;
import java.util.Set;

public class PathUtil {

    public static<T> Path<?> getPath(Root<T> root,String key){
        Path path =null;
        Assert.notNull(key, "key must not null");
        if (key.contains(".")){
            String[] keys = StringUtils.split(key, ".");
            Join join = joinAdd(root,JoinType.LEFT,keys);
            path = join.get(keys[keys.length-1]);
        }else {
            path = root.get(key);
        }
        Assert.notNull(path, "path must not null but path "+key+"is not exit");
        return  path;
    }

    /*检查最后一张表是否最在联表*/
    private static boolean joinIsExit(String lasstSecond,Set<Join<?,?>> joinSet) {
        Iterator<Join<?,?>> iterator =joinSet.iterator();
        Join join = null;
        while (iterator.hasNext()) {
            if (lasstSecond.equals(iterator.next().getAttribute().getName())){
                return true ;
            }
        }
        return false ;
    }

    private static Join<?,?> joinAdd(Root root, JoinType joinType,String[] keys) {
        Set<Join<?,?>> joinSet = root.getJoins();
        Join join = null;
        if (!joinIsExit(keys[keys.length -2],joinSet)){
            join =root.join(keys[0],joinType);
            for (int i = 1; i < keys.length-1; i++) {
                join = join.join(keys[i],joinType);
            }
        }
        return join;
    }
}

package com.standard.orm.componet.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 此类为一个表达式实例：
 * 分为两种模式：
 *        单列表达式：
 *          单列表达式结构如下，表示的是意义（ fieldname like %张三%）：
 *            {
 *                unique：1,
 *                key："name",
 *                type:"like",
 *                value: "张三"
 *            }
 *            此表达式支持联表也可以如下，只是jpa联表查询，不建议联表太深，
 *            {
 *                unique：1,
 *                key："person.name",
 *                type:"like",
 *                value: "张三"
 *            }
 *         复合表达式，理论上可以无限递归,列如 password eq  12345 and (name eq 张三 and (sex eq 女 or age lt 18  ))
 *         {
 *                unique：0,
 *                joinType："and",
 *                expressions:[ {unique：1,key："password",type:"eq",value: 12345},
 *                              {unique:0,joinType: and,expressions[
 *                                                             {unique：1,key："name",type:"eq",value: 张三}
 *                                                             {unique:0,joinType: or,expressions[
 *                                                                      {unique：1,key："sex",type:"eq",value: 女}
 *                                                                      {unique：1,key："age",type:"lt",value: 18}
 *                                                                       ]}
 *                                                                   ]}
 *                                                             ]}
 *                              ],
 *            }
 * 设计此表达式只是为了支持，较为复杂的查询，
 * 为了实现后台管理或者某些功能不太复杂的业务逻辑的接口，改变参数就可实现动态查询，
 * 只为base工程，可以提供更多的数据增删改查，从而减少代码量,
 * 此表达是的实构建已提供js支持，前端可以比较轻松的实现此表达式的构建，详情参见JS实现工具
 * */
@Getter
@Setter
public class Expression implements Serializable {
    private static final long serialVersionUID = -3564092744884569360L;
    //是否单列表达式（没有子表达式）0,1
    private int   unique;
    //子表表达式之间的连接符号 and or
    private String joinType;
    // 子表达式
    private ArrayList<Expression> expressions =new ArrayList<>();
    // 字段名字，下面几个字段与上面 javatype 和express时是冲突的，会根据unique拼接条件
    private String key ;
    //field  in ,like
    private String type;
    //field 值
    private Object value;
    //字段名对应的jpa路径
    private Path path;

    public  Path getPath(Root root){
        if (!ObjectUtils.isEmpty(this.path)){
            return this.path;
        }
        Assert.notNull(this.key, "key值不能为空");
        if (this.key.contains(".")) {
            String[] keys = StringUtils.split(this.key, ".");
            assert keys != null;
            this.path = root.get(keys[0]);
            Class clazz = this.path.getJavaType();
            if (Set.class.isAssignableFrom(clazz)) {
                SetJoin setJoin = root.joinSet(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    this.path = i==1?setJoin.get(keys[i]):path.get(keys[i]);
                }
            } else if (List.class.isAssignableFrom(clazz)) {
                ListJoin listJoin = root.joinList(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    this.path = i==1?listJoin.get(keys[i]):this.path.get(keys[i]);
                }
            } else if (Map.class.isAssignableFrom(clazz)) {
                MapJoin mapJoin = root.joinMap(keys[0]);
                for (int i = 1; i <keys.length ; i++) {
                    this. path = i==1?mapJoin.get(keys[i]):path.get(keys[i]);
                }
            } else {
                //是many to one时
                for (int i = 1; i <keys.length ; i++) {
                    this.path = path.get(keys[i]);
                }
            }
        }else {//基本类型
            this.path = root.get(key);
        }
        return  this.path;
    }


}

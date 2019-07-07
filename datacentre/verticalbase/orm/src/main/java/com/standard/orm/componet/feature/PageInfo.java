package com.standard.orm.componet.feature;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *jpa 和mybatis 的分页实现不同,为方便后期扩展，
 * 自定义一个分页尸体，方便返回统一的分页信息
 * */
@Component
@Data
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 2587523436890037070L;
    //当前页码
    private int pageNum;
    // 页面大小
    private int pageSize;
    // 开始行
    private int startRow;
    // 结束行
    private int endRow;
    //总行数
    private long total;
    // 总页码数
    private int pages;
    //根据排序字段
    private String orderBy;
    //实体队列
    private List<T> list;

   public PageInfo() {}

   public PageInfo(List<T> list,int pageNumber, int pageSize) {
       this.pageNum =pageNumber;
       this.pageSize =pageSize;
       this.total = list.size();
       this.startRow =  (this.pageNum-1)*this.pageSize;
       this.pages = (list.size()% this.pageSize)>0?list.size()/ this.pageSize+1:list.size()/ this.pageSize;
       if (this.total> this.startRow+this.pageSize){
           this.endRow =this.startRow+this.pageSize;
        } else if (this.total>=this.startRow&&this.total<this.startRow+this.pageSize){
            this.endRow = (int) this.total;
        }else {
           this.endRow = this.startRow;
        }
        List<T> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i>=this.startRow&&i<this.endRow) {
                list1.add(list.get(i));
            }
        }
        this.list =list1;
   }

}

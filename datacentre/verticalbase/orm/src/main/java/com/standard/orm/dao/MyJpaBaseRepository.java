package com.standard.orm.dao;

import com.standard.orm.componet.feature.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;


/**
 *继承勒JpaRepository的接口，findN那些所有方法，
 *JpaSpecificationExecutor 的执行器所有方法
 * */
@NoRepositoryBean
public interface MyJpaBaseRepository<T,ID extends Serializable>  extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    DynamicDelete<T> getDynamicDelete();

    DynamicTypeSelect<T> getDynamicTypeSelect();

    DynamicTupleSelect<T> getDynamicTupleSelect();

    DynamicUpdate<T> getDynamicUpdate();

    DynamicPageTypeSelect<T> readPageType(int pageNum, int pageSize);

    DynamicPageTupleSelect<T> readPageMap(int pageNum, int pageSize);
}

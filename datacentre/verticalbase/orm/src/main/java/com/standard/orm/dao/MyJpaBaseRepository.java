package com.standard.orm.dao;

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
}

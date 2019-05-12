package com.standard.orm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface JpaBaseDao <T,ID extends Serializable>  extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}

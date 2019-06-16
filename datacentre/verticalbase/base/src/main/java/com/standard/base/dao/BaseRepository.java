package com.standard.base.dao;

import com.standard.orm.dao.MyJpaBaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends MyJpaBaseRepository<T,ID>{
}

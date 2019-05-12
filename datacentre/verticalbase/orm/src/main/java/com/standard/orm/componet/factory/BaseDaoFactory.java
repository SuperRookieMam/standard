package com.standard.orm.componet.factory;


import com.standard.orm.dao.Impl.BaseDaoImpl;
import com.standard.orm.dao.BaseDao;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
/**
 * 在初始化DAO 如果继承了我自定义dao反悔我自定义的实现类，
 * 如果没有继承按照Jpa进行下去
 * 更多详情参见源码
 * */
public class BaseDaoFactory <T, ID extends Serializable>  extends JpaRepositoryFactory {

    public BaseDaoFactory(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    public  JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        /**
         * 是否继承我自定的JPa借口，如果继承了反悔自定义的实现类
         * */
        if (BaseDao.class.isAssignableFrom(information.getRepositoryInterface())){
            return new BaseDaoImpl<>(information.getDomainType(),entityManager);
        }
        JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(information.getDomainType());
        Object repository = this.getTargetRepositoryViaReflection(information, new Object[]{entityInformation, entityManager});
        Assert.isInstanceOf(JpaRepositoryImplementation.class, repository);
        return (JpaRepositoryImplementation)repository;
    }
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (BaseDao.class.isAssignableFrom(metadata.getRepositoryInterface())){
            return BaseDao.class;
        }
        return SimpleJpaRepository.class;
    }
}

package com.standard.orm.componet.factory;


import com.standard.orm.componet.util.SpringUtil;
import com.standard.orm.dao.Impl.MyJpaBaseRepositoryImpl;
import com.standard.orm.dao.MyJpaBaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

/**
 * 在初始化DAO 如果继承了我自定义dao反悔我自定义的实现类，
 * 如果没有继承按照Jpa进行下去
 * 更多详情参见源码
 * */
public class BaseDaoFactory  extends JpaRepositoryFactory {

    public BaseDaoFactory(EntityManager entityManager) {
        super(entityManager);
    }
    @Override
    public  JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        /**
         * 是否继承我自定的JPa接口，如果继承了返回自定义的实现类
         * */
        if (MyJpaBaseRepository.class.isAssignableFrom(information.getRepositoryInterface())){
            // 判断 子类接口是否有子类的实现类
            String implName =getRepositoryImpl(information.getRepositoryInterface());
            //如果名字相同,则证明只有接口本身,子类无实现类
            if (implName.equals(information.getRepositoryInterface().getName())){
                return new MyJpaBaseRepositoryImpl<>(information.getDomainType(),entityManager);
            }else {//否则子类存在实现类
                Class clazz =null;
                try {//加载子类实现类
                    clazz = Class.forName(implName);
                    Constructor constructor = clazz.getConstructor(information.getDomainType(),entityManager.getClass());
                    return (MyJpaBaseRepositoryImpl)constructor.newInstance(information.getDomainType(),entityManager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //  如果没有继承我的借口直接按照原来的方式执行
        JpaEntityInformation<?, Serializable> entityInformation = this.getEntityInformation(information.getDomainType());
        Object repository = this.getTargetRepositoryViaReflection(information, new Object[]{entityInformation, entityManager});
        Assert.isInstanceOf(JpaRepositoryImplementation.class, repository);
        return (JpaRepositoryImplementation)repository;
    }
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (MyJpaBaseRepository.class.isAssignableFrom(metadata.getRepositoryInterface())){
            return MyJpaBaseRepository.class;
        }
        return SimpleJpaRepository.class;
    }
    /**
     * 获取接口的实现类的名字，如果没有返回借口本身的名字
     * */
    public String getRepositoryImpl(Class<?> clazz){
        String[] beanNames =SpringUtil.getBeanNamesByType(clazz);
        Assert.isTrue(beanNames.length>2,"发现两处Dao实现，请使用一个实现");
        if (beanNames.length==1){
            return beanNames[0];
        }else {
            for (int i = 0; i < beanNames.length; i++) {
                if (!beanNames[i].equals(clazz.getName())){
                    return beanNames[i];
                }

            }
        }
        return beanNames[0];
    }
}

package com.standard.orm.componet.factory;


import com.standard.orm.dao.Impl.MyJpaBaseRepositoryImpl;
import com.standard.orm.dao.MyJpaBaseRepository;
import com.sun.istack.internal.NotNull;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在初始化DAO 如果继承了我自定义dao反悔我自定义的实现类，
 * 如果没有继承按照Jpa进行下去
 * 更多详情参见源码
 * */
public class BaseDaoFactory  extends JpaRepositoryFactory {
    public static final Map<String,List<Class>> intterFaceMap= new HashMap<>();

    public BaseDaoFactory(EntityManager entityManager) {
        super(entityManager);
        try {
            BaseDaoFactory.initInterFaceImpl();
            System.out.println("初始.....");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw  new RuntimeException("初始化失败");
        }
    }
    @Override
    public  JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        /**
         * 是否继承我自定的JPa接口，如果继承了返回自定义的实现类
         * */
        if (MyJpaBaseRepository.class.isAssignableFrom(information.getRepositoryInterface())){
            // 判断 子类接口是否有子类的实现类
            Class aClass =getRepositoryImpl(information.getRepositoryInterface());
            Constructor constructor = null;
            MyJpaBaseRepositoryImpl myJpaBaseRepository =null;
            try {
                if (aClass.getName().equals(MyJpaBaseRepositoryImpl.class.getName())){
                    myJpaBaseRepository =new MyJpaBaseRepositoryImpl(information.getDomainType(),entityManager);
                }else {
                    constructor = aClass.getConstructor(information.getDomainType(),entityManager.getClass());
                    myJpaBaseRepository =(MyJpaBaseRepositoryImpl)constructor.newInstance(information.getDomainType(),entityManager);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return myJpaBaseRepository ;
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
    public Class getRepositoryImpl(Class<?> clazz){
        List<Class> classList=intterFaceMap.get(clazz.getName());
        Assert.isTrue(classList.size()<1,"发现两处Dao实现，请使用一个实现");
        return classList.size()==0?MyJpaBaseRepositoryImpl.class:classList.get(0);
    }
    private static void initInterFaceImpl() throws ClassNotFoundException {
       String classPath =BaseDaoFactory.class.getResource("/").getPath();
       File file =new File(classPath);
       classPath = file.getPath();
       List<Class> interfaceList =new ArrayList<>();
       List<Class> classList =new ArrayList<>();
       getMyJpaBaseRepositorySubClass(file,classPath,interfaceList,classList);
       getInterfaseImpl(interfaceList,classList);
    }
    private static  void getMyJpaBaseRepositorySubClass(@NotNull File file,String classPath,List<Class> interfaceList,List<Class> classList) throws ClassNotFoundException {
        if (file.isFile()&&file.getName().endsWith(".class")){

             String className =file.getPath().replace(classPath+File.separator,"");
             String regix =File.separator.equals("\\")?"\\\\":"/";
             className = className.replaceAll(regix,".");
             className =className.substring(0,className.lastIndexOf("."));
             System.out.println(className);
             Class tclass = Class.forName(className);
             if (tclass.isInterface()&&MyJpaBaseRepository.class.isAssignableFrom(tclass)){
                 interfaceList.add(tclass);
             }else if (!tclass.isInterface()){
                 classList.add(tclass);
             }
        }else if (file.isDirectory()){
           File[] files = file.listFiles();
           for(File ele:files){
               getMyJpaBaseRepositorySubClass(ele,classPath,interfaceList,classList);
           }
        }
    }

    private static void  getInterfaseImpl(List<Class> interfaceList,List<Class> classList) {
            for (Class interfaces:interfaceList){
                List<Class> list =new ArrayList<>();
                for (Class clazz:classList){
                    if (interfaces.isAssignableFrom(clazz)){
                        list.add(clazz);
                    }
                }
                intterFaceMap.put(interfaces.getName(),list);
             }
    }

}

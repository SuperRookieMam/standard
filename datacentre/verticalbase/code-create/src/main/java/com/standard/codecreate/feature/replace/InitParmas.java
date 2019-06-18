package com.standard.codecreate.feature.replace;

import java.util.ArrayList;
import java.util.List;


public class InitParmas {

    public List<String> HowReplace(List<String> list,Class clazz) {
        List<String> list1 =new ArrayList<>();
        ReplacePlace.HowReplace(ele->{
            String returnStr =null;
            switch (ele){
                case "~[packagedao]":
                   String packgename = clazz.getPackage().getName();
                   packgename =packgename.substring(0,packgename.indexOf("."))+".dao";
                   returnStr ="~[packagedao],"+packgename;
                   return  returnStr;
                case "~[entityname]":
                   returnStr ="~[entityname],"+clazz.getName();
                    return  returnStr;
                case "~[entitySimpleName]":
                    returnStr ="~[entitySimpleName],"+clazz.getSimpleName();
                    return  returnStr;
                case "~[ID]":
                    returnStr ="~[ID],"+"Long";
                    return  returnStr;
                case "~[packageService]":
                    String packgeservice = clazz.getPackage().getName();
                    packgeservice =packgeservice.substring(0,packgeservice.indexOf("."))+".service";
                    returnStr ="~[packageService],"+packgeservice;
                    return  returnStr;
                case "~[packageServiceImpl]":
                    String packgeserviceImpl = clazz.getPackage().getName();
                    packgeserviceImpl =packgeserviceImpl.substring(0,packgeserviceImpl.indexOf("."))+".service.impl";
                    returnStr ="~[packageServiceImpl],"+packgeserviceImpl;
                    return  returnStr;
                case "~[parantService]":
                    String parantService = clazz.getPackage().getName();
                    parantService =parantService.substring(0,parantService.indexOf("."))+".service."+clazz.getSimpleName()+"Service";
                    returnStr ="~[parantService],"+parantService;
                    return  returnStr;
                case "~[packagecontroller]":
                    String packagecontroller = clazz.getPackage().getName();
                    packagecontroller =packagecontroller.substring(0,packagecontroller.indexOf("."))+".controller";
                    returnStr ="~[packagecontroller],"+packagecontroller;
                    return  returnStr;
                case "~[entitySimpleNameFirstLower]":
                    String entitySimpleNameFirstLower = clazz.getSimpleName();
                    entitySimpleNameFirstLower =entitySimpleNameFirstLower.substring(0,1).toLowerCase()+entitySimpleNameFirstLower.substring(1);
                    returnStr ="~[entitySimpleNameFirstLower],"+entitySimpleNameFirstLower;
                    return  returnStr;
                 }
            return returnStr;
            },list);
       return list1;
    }
}

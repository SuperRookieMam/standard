package com.standard.codecreate.feature.replace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplacePlace {

    public static Map<String,String> HowReplace(ReplaceParam replaceParam, List<String> list){
        Map<String,String> map =new HashMap<>();
        list.forEach(ele ->{
           String value = replaceParam.replaceParams(ele);
           if (value!=null||!"".equals(value)){
               String[] values =value.split(",");
               map.put(values[0],values[1]);
           }
        });
        return map;
    }
}

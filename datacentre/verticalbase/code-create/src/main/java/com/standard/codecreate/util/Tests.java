package com.standard.codecreate.util;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    public static void main(String[] args) throws Exception {
        List<String> list =new ArrayList<>();
        list.add("*.java");
        list.add("*.iml");
        list.add("/target/");
        new CopyDirectory().copyDirectiory("E:\\standard\\datacentre\\verticalbase\\code-create","C:\\Users\\Administrator\\Desktop\\target",list);
       new CompileUtils().compiler("UTF-8",
                                         "C:\\javaEnv\\maven repository\\javax\\persistence\\persistence-api\\1.0\\persistence-api-1.0.jar;C:\\javaEnv\\maven repository\\org\\projectlombok\\lombok\\1.18.6\\lombok-1.18.6.jar;C:\\javaEnv\\maven repository\\javax\\persistence\\javax.persistence-api\\2.2\\javax.persistence-api-2.2.jar",
                                      "E:\\standard\\datacentre\\verticalbase\\code-create",
                                    "src",
                                    "C:\\Users\\Administrator\\Desktop\\target",
                                  null);
    }
}

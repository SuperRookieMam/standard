package com.standard.codecreate;

import com.standard.codecreate.util.CreateFileUtil;

public class Tests {
    public static void main(String[] args) throws Exception {


        /**
         * 拷贝文指定文件夹倒指定的未知，可以根据后缀等来忽略拷贝
         * */
       /* List<String> list =new ArrayList<>();
        list.add("*.java");
        list.add("*.iml");
        list.add("/target/");
        String sourceDir ="E:\\standard\\datacentre\\verticalbase\\code-create";
        String targetDir ="C:\\Users\\Administrator\\Desktop\\target";
        new CopyDirectory().copyDirectiory(sourceDir,targetDir,list);

        *//**
         * javac 执行的编译，非maven项目，但是需要依赖jar报未知
         * 传统非maven项目用这个打包很方便
         * *//*
        String encoding ="UTF-8";
        String jars ="C:\\javaEnv\\maven repository\\javax\\persistence\\persistence-api\\1.0\\persistence-api-1.0.jar;" +
                     "C:\\javaEnv\\maven repository\\org\\projectlombok\\lombok\\1.18.6\\lombok-1.18.6.jar;" +
                     "C:\\javaEnv\\maven repository\\javax\\persistence\\javax.persistence-api\\2.2\\javax.persistence-api-2.2.jar";
        String filePath ="E:\\standard\\datacentre\\verticalbase\\code-create";
        String sourceName ="src";
        String targetPath ="C:\\Users\\Administrator\\Desktop\\target";
        new CompileUtils().javacCompiler(encoding,
                                         jars,
                                         filePath,
                                         sourceName,
                                         targetPath,
                                  null);
        *//**
         * 调用maven提供的工具实现java 编译maven项目
         * *//*
        String pomPath ="E:\\standard\\datacentre\\testProject\\pom.xml";
        String command ="compile";
        String mavenHome ="c:/javaEnv/maven-3.3.3";
        new CompileUtils().mavenCompile(pomPath,command,mavenHome);*/

        CreateFileUtil.createAllFile("com.standard.codecreate.feature.entity",
                    "E:\\standard\\datacentre\\verticalbase\\code-create\\src\\main\\java\\com\\standard\\codecreate\\feature\\template",
                    "C:\\Users\\Administrator\\Desktop");



    }
}

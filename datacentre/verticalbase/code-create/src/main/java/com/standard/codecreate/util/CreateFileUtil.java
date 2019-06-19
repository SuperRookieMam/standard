package com.standard.codecreate.util;

import com.standard.codecreate.feature.annotation.IsCreate;

import java.io.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateFileUtil {

    public static void main(String[] args) {
      File file =  new File("E:\\standard\\datacentre\\verticalbase\\code-create\\src\\main\\java\\com\\standard\\codecreate\\feature\\template\\ControllerTemplate.template");
        System.out.println(file.getName());
    }

    public static void createJavaFile(String packageName,String templatePath) throws Exception {
       File file = getFileByPackage(packageName);
       List<Class> list =new ArrayList<>();
       getAllClass(file,list);
       Map<String ,List<String>> map =new HashMap<>();
       getTemplateFils(templatePath,map);
       List<String> list1 =map.get("DaoTemplate");


    }



    private static File getFileByPackage(String packageName){
        String classes =  CreateFileUtil.class.getClassLoader().getResource("").getPath();
        classes +=packageName.replaceAll("\\.", "/");
        File file =new File(classes);
        if (!file.exists()){
            throw  new RuntimeException("包名不存在");
        }
        return file;
    }

    private   static void getAllClass(File file,List<Class> list) throws ClassNotFoundException {
         if (file.isFile()&&file.getName().endsWith(".class")){
            String classpackge = file.getPath();
            classpackge =classpackge.substring(classpackge.indexOf("classes\\")+8);
            String re = File.separator.equals("\\")?"\\\\":"/";
             classpackge = classpackge.replaceAll(re,"\\.");
             Class clazz =Class.forName(classpackge);
             Annotation annotation =clazz.getAnnotation(IsCreate.class);
             if (annotation!=null&&((IsCreate)annotation).on()){
                 list.add(clazz);
             }
         }else {
            File[] files = file.listFiles();
             for (File file1:files){
                 getAllClass(file1,list);
             }
         }
    }



    private static void getTemplateFils(String path, Map<String,List<String>> map) throws Exception {
        File file =new File(path);
        if (!file.exists()&&!file.isDirectory()){
            throw  new  RuntimeException("目录不存在");
        }
        File[] files =file.listFiles();
        for (File file1:files){
            if (file1.isFile()&&file1.getName().endsWith(".template")){
                String fileName =file1.getName().substring(0,file1.getName().lastIndexOf("."));
                map.put(fileName,getFileStr(file1));
            }
        }
    }

    private static List<String> getFileStr(File file) throws Exception {
        List<String> list =new ArrayList<>();
        FileInputStream fileInputStream =new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");
        BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
        String line ="";
        while ((line=bufferedReader.readLine())!=null){
            list.add(line+System.getProperty("line.separator"));
        }
        fileInputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
        return list;
    }



}

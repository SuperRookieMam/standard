package com.standard.codecreate.util;

import java.io.*;
import java.util.List;

public class CopyDirectory {
    /**
     * 复制文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException
     */
    public void copyFile(File sourceFile, File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        // 关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    /**
     * 复制文件夹
     *
     * @param sourceDir 源文件夹路径
     * @param targetDir 目标文件夹路径
     * @param  ingnore 忽略的文件夹格式/name/ 忽略的文件 name.xx,*.xx
     * @throws IOException
     */
    public void copyDirectiory(String sourceDir, String targetDir, List<String> ingnore)
            throws IOException {
        File sfile =new File(sourceDir);
        String last = "/"+sfile.getPath().replace(sfile.getParent()+File.separator,"")+"/";
        if (ingnore.contains(last)){
            return;
        }
        File rootfile = new File(targetDir);
        if (!rootfile.exists()){
            rootfile.mkdir();
        }
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                if (ingnore.contains(file[i].getName())||
                        ingnore.contains("*"+file[i].getName().substring(file[i].getName().lastIndexOf(".")))){
                    continue;
                }
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir)
                        .getAbsolutePath()
                        + File.separator + file[i].getName());
                copyFile(sourceFile, targetFile);
            }
            if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2,ingnore);
            }
        }
    }
}

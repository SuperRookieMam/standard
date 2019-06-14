package com.standard.codecreate.util;

import javax.tools.*;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/****
 *
 */
public class CompileUtils {
	private String jars = "";
	private String targetDir = "";

	/**
	 * 判断字符串是否为空 有值为true 空为：false
	 */
	public boolean isnull(String str) {
		if (null == str) {
			return false;
		} else if ("".equals(str)) {
			return false;
		} else if (str.equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 编译java文件
	 *
	 * @param encoding    编译编码
	 * @param jars        需要加载的jar
	 * @param filePath    文件或者目录（若为目录，自动递归编译）
	 * @param sourceDir   java源文件存放目录
	 * @param targetDir   编译后class类文件存放目录
	 * @param diagnostics 存放编译过程中的错误信息
	 * @return
	 * @throws Exception
	 */
	public boolean compiler(String encoding, String jars, String filePath, String sourceDir, String targetDir, DiagnosticCollector<JavaFileObject> diagnostics)
			throws Exception {
		// 获取编译器实例
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 获取标准文件管理器实例
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		try {
			if (!isnull(filePath) && !isnull(sourceDir) && !isnull(targetDir)) {
				return false;
			}
			// 得到filePath目录下的所有java源文件
			File sourceFile = new File(filePath);
			List<File> sourceFileList = new ArrayList<File>();
			this.targetDir = targetDir;
			getSourceFiles(sourceFile, sourceFileList);
			// 没有java文件，直接返回
			if (sourceFileList.size() == 0) {
				System.out.println(filePath + "目录下查找不到任何java文件");
				return false;
			}
			// 获取要编译的编译单元
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFileList);
			/**
			 * 编译选项，在编译java文件时，编译程序会自动的去寻找java文件引用的其他的java源文件或者class。
			 * -sourcepath选项就是定义java源文件的查找目录，
			 * -classpath选项就是定义class文件的查找目录。
			 */
			Iterable<String> options = Arrays.asList("-encoding", encoding, "-classpath", jars, "-d", targetDir, "-sourcepath", sourceDir);
			JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
			// 运行编译任务
			return compilationTask.call();
		} finally {
			fileManager.close();
		}
	}

	/**
	 * 查找该目录下的所有的java文件
	 *
	 * @param sourceFile
	 * @param sourceFileList
	 * @throws Exception
	 */
	private void getSourceFiles(File sourceFile, List<File> sourceFileList) throws Exception {
		if (sourceFile.exists() && sourceFileList != null) {//文件或者目录必须存在
			if (sourceFile.isDirectory()) {// 若file对象为目录
				// 得到该目录下以.java结尾的文件或者目录
				File[] childrenFiles = sourceFile.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						if (pathname.isDirectory()) {
							return true;
						} else {
							String name = pathname.getName();
							if (name.endsWith(".java") ? true : false) {
								return true;
							}
							return false;
						}
					}
				});
				// 递归调用
				for (File childFile : childrenFiles) {
					getSourceFiles(childFile, sourceFileList);
				}
			} else {// 若file对象为文件
				sourceFileList.add(sourceFile);
			}
		}
	}

	/**
	 * 查找该目录下的所有的jar文件
	 *
	 * @param jarPath
	 * @throws Exception
	 */
	private String getJarFiles(String jarPath) throws Exception {
		File sourceFile = new File(jarPath);
		// String jars="";
		if (sourceFile.exists()) {// 文件或者目录必须存在
			if (sourceFile.isDirectory()) {// 若file对象为目录
				// 得到该目录下以.java结尾的文件或者目录
				File[] childrenFiles = sourceFile.listFiles(new FileFilter() {
					public boolean accept(File pathname) {
						if (pathname.isDirectory()) {
							return true;
						} else {
							String name = pathname.getName();
							if (name.endsWith(".jar") ? true : false) {
								jars = jars + pathname.getPath() + ";";
								return true;
							}
							return false;
						}
					}
				});
			}
		}
		return jars;
	}


}

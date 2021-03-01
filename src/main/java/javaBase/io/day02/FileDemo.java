package javaBase.io.day02;

import java.io.File;

/**
 * @description:  该类演示File类的一些用法，以及api方法演示
 * @author: gezx
 * @date: 2021/3/1 20:30
 */
/*
    File： 文件和目录路径名的抽象表现形式，File的实例是不可变的

    构造方法：
        File(File parent,String child)
        File(String pathname)
        File(String parent，String child)

    File的常用功能：
        创建功能：
            boolean creatNewFile()
            boolean mkdir()
            boolean mkdirs()
        删除功能：
            boolean delete()
        获取功能
 * 			File getAbsoluteFile()
 * 			String getAbsolutePath()
 * 			String getName()
 * 			String getParent()
 * 			File getParentFile()
 * 			String getPath()
  			long lastModified()
  			long length()
 * 		判断功能
 * 			 boolean exists()
 * 			 boolean isAbsolute()
 * 			 boolean isDirectory()
 			 boolean isFile()
 			 boolean isHidden()
 		修改文件名：
 			boolean renameTo(File dest)
 */

public class FileDemo {
    public static void main(String[] args) {
        // File(String pathname): 将指定的路径名转换为一个file对象
        File file = new File("D:\\codetest\\a.txt");

        // File(String parent,string child): 根据指定的父路径和文件路径创建File对象
        File file1 = new File("D:\\codetest", "a.txt");

        // File(File parent,String child): 根据指定的父路径对象和文件路径创建File对象
        File file2 = new File(new File("D:\\codetest"),"a.txt");
    }
}

package javaBase.io.day03.demo_02;

import java.io.File;

/**
 * @description:     需求： 输出指定目录下所有的Java文件的名称（包含子目录下的）
 * @author: gezx              求解这样的题目可以分步骤进行编程：
 *                          （1）第一步：先输出指定目录下的所有Java文件名称（不包含子目录下的）
 *                          （2）第二部：在输出题目要求的
 * @date: 2021/3/1 23:50
 */
public class RecurrenceTest {

    public static void main(String[] args) {
        File f = new File("src");
        //File f = new File("src\\com\\itheima_01\\RecurrenceDemo.java");
        method(f);
    }

    // 输出指定目录下的所有java文件的名称（包含子目录）
    public static void method(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                // 判断是否是文件对象
                if(f.isFile()){
                    if(f.getName().endsWith(".java")){
                        System.out.println(f.getName());
                    }
                }else {
                    // 否则是一个目录，则递归调用自己，输出子目录下的Java文件名称
                    method(f);
                }
            }
        }
    }


    // 输出指定目录下的所有java文件的名称（不包含子目录）
    public static void method2(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                // 判断是否是文件对象
                if(f.isFile()){
                    if(f.getName().endsWith(".java")){
                        System.out.println(f.getName());
                    }
                }
            }
        }
    }
}

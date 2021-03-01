package javaBase.io.day02;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: gezx
 * @date: 2021/3/1 21:23
 */
/*
 *	判断功能
 * 		boolean exists()
 * 		boolean isAbsolute()
 * 		boolean isDirectory()
 		boolean isFile()
 		boolean isHidden()
 */
public class FileDemo3 {
    public static void main(String[] args) throws IOException {
     //   method();

    //    method2();

        // 其他两个方法就不写了非常简单

    }

    private static void method2() {
        File file = new File("a.txt");
        File file2 = new File("D:\\codetest\\a.txt");

        //    boolean isAbsolute():  判断file对象所指的路径是否是绝对路径，如果是绝对路径则返回true，否则返回false
        System.out.println(file.isAbsolute());
        System.out.println(file2.isAbsolute());
    }

    private static void method() {
        File file = new File("a.txt");
        //    file.createNewFile();
        // boolean exists(): 判断文件或者文件夹是否存在，如果存在则返回true否则返回false
        System.out.println(file.exists());
    }
}

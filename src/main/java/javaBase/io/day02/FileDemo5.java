package javaBase.io.day02;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: gezx
 * @date: 2021/3/1 23:13
 */
/*
 * 	String[] list()
 	File[] listFiles()
 	static File[] listRoots()
 */
public class FileDemo5 {
    public static void main(String[] args) throws IOException {
    //    method();

    //    method2();  方法2还有些问题

    }

    private static void method2() throws IOException {
        File f = new File("b\\c\\d");
        f.createNewFile();
        //    File f2 = new File("D:\\workspace\\myFile");
        File f3 = new File("c.txt");

        // File[] listFiles(): 返回当前路径下所有的文件和文件夹
        // 注意：只有指向文件夹的File对象才可以调用该方法
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            System.out.println(file.getName());
        }
    }

    private static void method() {
        File f = new File("b");
        //    File f2 = new File("D:\\workspace\\myFile");
        File f3 = new File("c.txt");

        // String[] list(): 返回当前路径下所有的文件和文件夹名称
        // 注意：只有指向文件夹的File对象才可以调用该方法
        String[] files = f3.list();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}

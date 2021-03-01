package javaBase.io.day01.demo_02;

import java.io.FileReader;
import java.io.IOException;

/**
 * @className: FileReaderDemo
 * @description:        需求：从文件中读数据，并显示到控制台
 *                  读数据 —— 输入流 —— FileReader
 *                  FileReader:
 *                      FileReader(string fileName)  传递一个文件名称
 *               输入流读文件的步骤：
 *               1、创建输入流对象
 *               2、调用输入流对象的读数据方法
 *               3、释放资源
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("fr.txt");
        FileReader fw = new FileReader("FileWriteDemo.java");

//        // 调用输入流的读数据方法，这个方法是一次读取一个字符
//        int i = fileReader.read();     // 第一次读取
//        System.out.println(i);
//        System.out.println((char)i);
//
//        i = fileReader.read();     // 第二次读取
//        System.out.println(i);
//        System.out.println((char)i);
//
//        i = fileReader.read();     // 第三次读取
//        System.out.println(i);
//        System.out.println((char)i);



        // 这个时候我们发现代码重复度很高，可以通过循环来把文件字符全部读出，并写到控制台；并且通过测试我们发现，读取的数据返回值是-1的时候，
        // 就说明没有数据了，文件全部读完了，这个也是我们循环结束的条件
        int data;
        while((data = fileReader.read())!=-1){
            System.out.print((char)data);    // 读到什么输出什么，不需要换行输出
        }
        fileReader.close();

        System.out.println();

        int str;
        while((str=fw.read())!=-1){
            System.out.print((char)str);
        }
        fw.close();

    }
}

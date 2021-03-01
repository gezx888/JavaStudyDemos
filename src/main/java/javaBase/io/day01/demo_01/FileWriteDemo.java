package javaBase.io.day01.demo_01;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: FileWriteDemo
 * @description:  该类演示 FileWrite 类的使用
 *                  需求：往文件里面写数据，写数据，输出流 ——— fileWrite
 *                  FileWrite：
 *                  FileWrite(String name) 传递一个文件名称
 *              输出流写数据的步骤：
 *                  1、创建输出流对象
 *                  2、调用输出流对象的写数据方法
 *                  3、释放资源
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileWriteDemo {
    public static void main(String[] args) throws IOException {
        // 创建输出流对象
        FileWriter fileWriter = new FileWriter("F:\\codeTest\\a.txt");
        fileWriter.write("hello world!");  // 调用输出流对象的写数据方法  其实你看该方法有五种重载方法可供选择
        // 写完数据还只是在内存缓冲区中，需要调用该方法或是直接释放资源也会就数据写到文件
        fileWriter.flush();
        fileWriter.close();    // 释放资源，通知系统释放和改文件相关的资源
    }
}

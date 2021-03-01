package javaBase.io.day01.demo_03;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: CopyFileDemo
 * @description:           需求：把项目路径下的FileWriteDemo.java 文件 读取复制到项目路径下copy.java 文件
 *
 *                          源数据： FileWriteDemo.java    读数据    FileReader
 *                         目的数据： copy.java     输出数据    FileWrite
 * @author: gezx
 * @date: 2021/3/1
 **/
public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        // 创建输入流对象
        FileReader fileReader = new FileReader("FileWriteDemo.java");
        // 创建输出流对象
        FileWriter fileWriter = new FileWriter("copy.java");

        /*// 读写数据 一次读写一个字符数据
        int len;
        while((len = fileReader.read()) != -1){
            fileWriter.write(len);
        }*/

        // 读写数据  一次读写一个数组的数据
        char[] chars = new char[1024];
        int len;
        while((len=fileReader.read(chars)) != -1){
            fileWriter.write(chars,0,len);
        }

        // 释放资源
        fileWriter.close();
        fileReader.close();
    }
}

package javaBase.io.day01.demo_05;

import java.io.*;

/**
 * @description:  需求：把项目路径下的FileWriteDemo.java 复制到项目路径下copy.java
 *                  数据源：
 *                  FileWriteDemo.java    读数据    fileReader   高效的读数据  ---  BufferedReader
 *                  目的地数据：
 *                  copy.java        写数据     fileWrite    高效的写数据   BufferedWriter
 *
 *                 本类 演示缓冲输入输出流的特殊性功能复制文件的方法
 *
 * @author: gezx
 * @date: 2021/3/1 19:30
 */
public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        // 创建输入缓冲流对象
        BufferedReader reader = new BufferedReader(new FileReader("FileWriteDemo.java"));
        // 创建输出缓冲流对象
        BufferedWriter writer = new BufferedWriter(new FileWriter("copy.java"));

        //  读写数据
        String line;
        while((line = reader.readLine()) != null){
            writer.write(line);
            writer.newLine();
            writer.flush();
        }

        // 释放资源
        writer.close();
        reader.close();

    }
}

package javaBase.io.day01.demo_04;

import java.io.*;

/**
 * @description:  需求：把项目路径下的FileWriteDemo.java 复制到项目路径下copy.java
 *                  数据源：
 *                  FileWriteDemo.java    读数据    fileReader   高效的读数据  ---  BufferedReader
 *                  目的地数据：
 *                  copy.java        写数据     fileWrite    高效的写数据   BufferedWriter
 * @author: gezx
 * @date: 2021/3/1 19:30
 */
public class CopyFileDemo {
    public static void main(String[] args) throws IOException {
        // 创建输入缓冲流对象
        BufferedReader reader = new BufferedReader(new FileReader("FileWriteDemo.java"));
        // 创建输出缓冲流对象
        BufferedWriter writer = new BufferedWriter(new FileWriter("copy.java"));

        /*// 读写数据  一次读写一个字符
        int ch;
        while((ch = reader.read()) != -1){
            writer.write(ch);
        }*/

        //  读写数据，一次读写一个字符数组的数据
        char[] chars = new char[1024];
        int len;
        while((len = reader.read(chars)) != -1){
            writer.write(chars,0,len);
        }

        // 释放资源
        writer.close();
        reader.close();

    }
}

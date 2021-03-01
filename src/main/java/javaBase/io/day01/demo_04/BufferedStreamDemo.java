package javaBase.io.day01.demo_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @className: BufferedStreamDemo
 * @description:    BufferedWrite: 将文本写入字符输出流，缓冲各个字符，从而实现提供单个字符、数组和字符串的高效写入
 *                  BufferedReader: 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取
 * @author: gezx
 * @date: 2021/3/1
 **/
public class BufferedStreamDemo {
    public static void main(String[] args) throws IOException {
        // 创建输出缓冲流对象
        /*BufferedWriter writer = new BufferedWriter(new FileWriter("bw.txt"));
        writer.write("hello world");
        writer.write("6666");
        writer.flush();
        writer.close();*/

        // 创建输入缓冲流对象
        BufferedReader reader = new BufferedReader(new FileReader("FileWriteDemo.java"));

        /*// 一次读取一个字符
        int len;
        while((len = reader.read()) != -1){
            System.out.print((char)len);
        }*/

        // 一次读取一个字符数组的数据
        char[] chars = new char[1024];
        int len;
        while((len = reader.read(chars)) != -1){
            System.out.print(new String(chars,0,len));
        }

        reader.close();
    }
}

package javaBase.io.day01.demo_05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @description:    缓冲流的特殊功能：
 *                    BufferedWrite
 *                      void newLine()：写一个换行符，这个换行符有系统决定
 *                   BufferedReader
 *                      String readLine()：一次读取一行数据，但是不读取换行符
 *
 * @author: gezx
 * @date: 2021/3/1 19:55
 */
public class BufferedStreamDemo {
    public static void main(String[] args) throws IOException {
        /*BufferedWriter writer = new BufferedWriter(new FileWriter("bw2.txt"));
        for (int i = 0; i < 10; i++) {
            writer.write("hello" + i);
        //    writer.write("\r\n");
            writer.newLine();     // 也是写入一行换行符，并且与系统无关，由系统决定
            writer.flush();
        }
        writer.close();*/

        // 创建缓冲输入流对象
        BufferedReader reader = new BufferedReader(new FileReader("fr.txt"));

        /*String line = reader.readLine();
        System.out.println(line);

        line = reader.readLine();
        System.out.println(line);

        line = reader.readLine();
        System.out.println(line);*/

        // 用循环来优化上述代码，并且是读文本文件的套路代码
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }

        // 释放资源
        reader.close();
    }
}

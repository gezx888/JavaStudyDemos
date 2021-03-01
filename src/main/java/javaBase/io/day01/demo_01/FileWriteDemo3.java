package javaBase.io.day01.demo_01;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: FileWriteDemo3
 * @description:            演示FileWrite类的几个写数据的重载方法
 *                      void write(String str):写一个字符串数据
 *  *                   void write(String str,int index,int len):写一个字符串中的一部分数据
 *  *                   void write(int ch):写一个字符数据,这里写int类型的好处是既可以写char类型的数据，也可以写char对应的int类型的值。'a',97
 *  *                   void write(char[] chs):写一个字符数组数据
 *  *                   void write(char[] chs,int index,int len):写一个字符数组的一部分数据
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileWriteDemo3 {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("F:\\codeTest\\a.txt");

        fw.write("hello world");
        fw.write("hello world",1,5);

        fw.write("b");

        char[] chars = {'a','b','c','d','e','f'};
        fw.write(chars);
        fw.write(chars,3,2);

        fw.close();
    }

}

package javaBase.io.day01.demo_02;

import java.io.FileReader;
import java.io.IOException;

/**
 * @className: FileReaderDemo
 * @description:
 *               输入流读文件的步骤：
 *               1、创建输入流对象
 *               2、调用输入流对象的读数据方法
 *               3、释放资源
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileReaderDemo2 {
    public static void main(String[] args) throws IOException {
        FileReader fw = new FileReader("FileWriteDemo.java");  // 相对路径

        // 调用输入流对象的读数据方法
  //      int read(char[] chars);  一次读取一个字符数组的数据，返回的是 实际 读取到的字符的个数

        /*char[] chs = new char[5];
        //第一次读数据
        int len = fw.read(chs);
        System.out.println("len:"+len);
        //System.out.println(new String(chs));
        System.out.println(new String(chs,0,len));

        //第二次读数据
        len = fw.read(chs);
        System.out.println("len:"+len);
        //System.out.println(new String(chs));
        System.out.println(new String(chs,0,len));*/

        /**
         * 我们发现读取的代码相似度极高，可以通过循环把文件全部读取，并进行输出
         * 通过测试，我们可以发现：如果实际读取数据返回值是-1的时候，说明已经读到文件结尾没有数据了，这个也是循环结束的条件
         */
        char[] chars = new char[1024];
        int length;
        while((length=fw.read(chars)) != -1){
            System.out.print(new String(chars,0,length));
        }

        fw.close();   // 释放资源

    }
}

package javaBase.io.day01.demo_01;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: FileWriteDemo2
 * @description:
 *                  相对路径：相对向前项目而言，在项目根目录下（a.txt）
 *                  绝对路径：以盘符开收的路径(F:\\codeTest\\a.txt)
 *
 *                  close() 和 flush() 的区别
 *                  flush() 刷新缓冲区，流对象还可以继续写入数据，继续被使用
 *                  close() 先刷新缓冲区，然后通知系统释放与文件相关的资源，流对象不能在被使用了
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileWriteDemo2 {
    public static void main(String[] args) throws IOException {
     //   FileWriter fileWriter = new FileWriter("F:\\codeTest\\a.txt");
        FileWriter fileWriter = new FileWriter("a.txt");

        fileWriter.write("helloworld");
        fileWriter.flush();
        fileWriter.write("sssss");
        fileWriter.flush();

        fileWriter.close();

        fileWriter.write("javaEE"); // 数据写不进去了
    }
}

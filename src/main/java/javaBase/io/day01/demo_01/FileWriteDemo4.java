package javaBase.io.day01.demo_01;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @className: FileWriteDemo4
 * @description:        如何实现数据的换行写入？
 *                        \n 可以实现换行，但是当使用windows自带的记事本发现并没有实现换行，这是因为windows识别的换行不是\n
 *                      而是\r\n    windows: \r\n
 *                                  linux:  \n
 *                                  mac:  \r
 *                    如何实现数据的追加写入？  使用另外一个重载的构造方法
 *                      FileWrite(String fileName，boolean append)
 *
 * @author: gezx
 * @date: 2021/3/1
 **/
public class FileWriteDemo4 {
    public static void main(String[] args) throws IOException {
    //    FileWriter fw = new FileWriter("F:\\codeTest\\a.txt");

        FileWriter fileWriter = new FileWriter("F:\\codeTest\\a.txt",true);   // 表示在文件后面追加写入，默认情况是false


//        for (int i = 0; i < 10; i++) {
//            fw.write("你好");
//            fw.write("\r\n");
//        }

        fileWriter.write("hello");

    //    fw.close();
        fileWriter.close();
    }
}

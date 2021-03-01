package javaBase.io.day02;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: gezx
 * @date: 2021/3/1 21:04
 */
/*
 * 	创建功能
 * 		boolean createNewFile()
 * 		boolean mkdir()
 * 		boolean mkdirs()
 * 	删除功能
 * 		boolean delete()
 *
 *  绝对路径：固定不可改变的路径，以盘符开头
 *  相对路径：相对某个参照物，不能以盘符开头
 *  		在eclipse中相对路径相对应当前项目的根目录
 *
 *  注意：删除一个文件夹，这个文件夹下面不能有其他的文件和文件夹
 */
public class FileDemo2 {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\codetest\\a.txt");  // 绝对路径
        File file1 = new File("a.txt");    // 相对路径

        //  createNewFile(): 当指定文件不存在时创建文件并返回true，否则返回false
     //   System.out.println(file1.createNewFile());

        //  boolean mkdir(): 当指定文件夹不存在时，创建文件夹并且返回true，否则返回false

        File file2 = new File("b");
     //   System.out.println(file2.mkdir());

        //  boolean mkdirs(): 创建文件夹,当文件夹所在目录不存在时，则顺到一块创建了
        File file3 = new File("c\\d\\e");
    //    System.out.println(file3.mkdir());
 //       System.out.println(file3.mkdirs());

        File f5 = new File("c.txt");
   //     System.out.println(f5.mkdir());

        // boolean delete(): 当指定的文件或是文件夹存在的时候删除文件或文件夹，并返回true，否则返回false
        System.out.println(f5.delete());

    }
}

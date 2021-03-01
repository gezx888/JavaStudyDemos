package javaBase.io.day03.demo_02;

import java.io.File;

/**
 * @description:        需求：删除指定的目录（包含子目录）
 *                  注意： 如果要删除一个目录，则需要先删除这个目录下的所有子文件和子目录
 * @author: gezx
 * @date: 2021/3/2 0:04
 */
public class RecurrenceTest2 {
    public static void main(String[] args) {
        File f = new File("D:\\codetest\\a");
        method(f);
    }


    public static void method(File file){
        if(file.isDirectory()){
            // 首先干掉自己下面的所有的子目录和子文件
            File[] files = file.listFiles();
            for(File f : files){
                if(f.isFile()){
                    // 是文件直接干掉
                    System.out.println(f.getName());
                    f.delete();
                }else if(f.isDirectory()){
                    // 继续查看是否还有文件和子目录
                    method(f);
                }
            }

            // 最后干掉自己
            System.out.println(file.getName());
            file.delete();
        }
    }
}

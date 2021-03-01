package javaBase.io.day02;

import java.io.File;

/**
 * @description:
 * @author: gezx
 * @date: 2021/3/1 23:06
 */
/*
 *	获取功能
 		File getAbsoluteFile()
 * 		String getAbsolutePath()
 * 		String getParent()
 * 		File getParentFile()
 * 		String getName()
 * 		String getPath()
  		long lastModified()
  		long length()
  	修改功能：
 		boolean renameTo(File dest)
 */
public class FileDemo4 {
    public static void main(String[] args) {
        method();
        // 其他的几个方法也不演示了，需要调用的时候查阅api即可
    }

    private static void method() {
        File file1 = new File("D:\\codetest\\a.txt");
        File file2 = new File("a.txt");

        // File getAbsoluteFile(): 以File对象的形式返回当前file对象所指向的绝对路径
        File absoluteFile = file1.getAbsoluteFile();
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absoluteFile);   // 发现返回的是一样的，就是后面的所能调用的api不一样了
        System.out.println(absolutePath);
    }
}

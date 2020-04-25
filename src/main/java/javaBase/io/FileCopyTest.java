package javaBase.io;

import java.io.*;

/**
 * @Describe: 本类主要演示文件复制的一些方式方法
 * @Author: Gezx
 * @Date: 2020/4/10 22:38
 */
public class FileCopyTest {
    public static void main(String[] args) throws IOException {
     //   streamCopyFile("D:\\mysql\\zb_unit_db.sql","D:\\mysql\\zip\\zb_unit_db.sql");
     //   bufferedStreamCopyFile("D:\\mysql\\zb_unit_db.sql","D:\\mysql\\zip\\zb_unit_db.sql");
        readerWriterCopyFile("D:\\mysql\\zb_unit_db.sql","D:\\mysql\\zip\\zb_unit_db.sql");
    }

    /**
     * 使用FileInputStream/FileOutPutStream字节流进行文件的复制
     */
    private static void streamCopyFile(String srcFile,String desFile) throws IOException {
        // 使用字节流进行文件复制
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(desFile);

        Integer by = 0;
        // 一次读取一个字节
        while((by = fis.read()) != -1){
            fos.write(by);
        }

        // 关闭资源
        fos.close();
        fis.close();
    }

    /**
     * 使用BufferedInputStream/BufferedOutputStream高效字节流进行复制文件
     */
    private static void bufferedStreamCopyFile(String srcFile,String desFile) throws IOException {
        // 使用缓冲字节流进行文件复制
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));

        byte[] b = new byte[1024];
        Integer len = 0;
        // 一次读取1024字节的数据
        while((len = bis.read(b)) != -1){
            bos.write(b);
        }

        // 关闭资源
        bos.close();
        bis.close();
    }

    /**
     * 使用FileReader/FileWriter字符流进行文件复制。（注意这种方式只能复制只包含字符的文件，也即是说用
     *  记事本打开该文件，能够读懂）
     */
    private static void readerWriterCopyFile(String srcFile,String desFile) throws IOException {
        // 使用字符流进行文件的复制
        FileReader fileReader = new FileReader(srcFile);
        FileWriter fileWriter = new FileWriter(desFile);

        Integer by = 0;
        while((by = fileReader.read()) != -1){
            fileWriter.write(by);
        }

        // 关闭资源
        fileWriter.close();
        fileReader.close();
    }

}

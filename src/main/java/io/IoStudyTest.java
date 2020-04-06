package io;

import org.junit.Test;

import java.io.IOException;

/**
 * @Describe: 本类主要演示Java输入输出流IO部分一些知识
 * @Author: Gezx
 * @Date: 2020/4/6 17:57
 */
public class IoStudyTest {

    @Test
    public void test01(){
        int b;
        try {
            System.out.println("please Input:");
            while((b = System.in.read()) != -1){
                System.out.print((char)b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

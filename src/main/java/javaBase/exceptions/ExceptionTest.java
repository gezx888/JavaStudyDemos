package javaBase.exceptions;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Describe: Java异常机制测试主类
 * @Author: Gezx
 * @Date: 2020/3/30 22:20
 */
public class ExceptionTest {

    public ExceptionTest(){}

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1(){
        boolean ret = true;
        try {
            ret = testEx2();
            if(!ret){
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        }catch (Exception e){
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        }finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx2(){
        boolean ret = true;
        try{
            int b= 12;
            int c;
            for(int i = 2;i >= -2;i--){
                c = b / i;
                System.out.println("i = " + i);
            }
            return true;
        }catch (Exception e){
            System.out.println("testEx2, catch Exception");
            ret = false;
            throw e;    // 注意这里抛出的是一个运行时异常，运行不做任何处理，编译也能通过
        }finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        ExceptionTest exceptionTest = new ExceptionTest();
        try {
            exceptionTest.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 例1  捕捉throw语句抛出的“除数为0”异常。
    @Test
    public void text01(){
        int a = 6;
        int b = 0;
        try{
            if(b == 0){
              throw new ArithmeticException();   // 通过throw语句抛出异常
    //        System.out.println("a/b的值是：" + a/b);上面已经通过throw语句抛出异常对象了，该句为不可能达语句，不注释没法编译通过
            }
            System.out.println("a/b的值是：" + a/b);  // 抛出异常后这里不执行，没抛出异常正常执行这句
        }catch(ArithmeticException e){  // catch捕获异常
            e.printStackTrace();
            System.out.println("程序出现异常，除数b不能为0");
        }catch(Exception e){  // 捕获程序可能出现的其他的异常
            e.printStackTrace();
            System.out.println("程序出现了其他未知异常");
        }
        System.out.println("程序正常结束");
    }

    // 例2  捕捉运行时系统自动抛出“除数为0”引发的ArithmeticException异常。
    @Test
    public void test02() {
        int a = 6;
        int b = 0;
        try {
            System.out.println("a/b的值是：" + a / b);
        } catch (ArithmeticException e) {
            System.out.println("程序出现异常，变量b不能为0。");
        }
        System.out.println("程序正常结束。");
    }

    // 例3  不捕捉、也不声明抛出 -- 运行时异常(允许由系统自动抛出)。
    @Test
    public void test03() {
        int a, b;
        a = 6;
        b = 0; // 除数b 的值为0
        System.out.println(a / b);
    }

    // 例4  程序可能存在除数为0异常和数组下标越界异常
    @Test
    public void test04(){
        int[] intArray = new int[3];
        try {
            for (int i = 0; i <= intArray.length; i++) {
                intArray[i] = i;
                System.out.println("intArray[" + i + "] = " + intArray[i]);
                System.out.println("intArray[" + i + "]模 " + (i - 2) + "的值:  "
                        + intArray[i] % (i - 2));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("intArray数组下标越界异常。");
        } catch (ArithmeticException e) {
            System.out.println("除数为0异常。");
        }
        System.out.println("程序正常结束。");
    }

    // 例5  带finally子句的异常处理程序。
    @Test
    public void test05(){
        int i = 0;
        String greetings[] = { " Hello world !", " Hello World !! ",
                " HELLO WORLD !!!" };
        while (i < 4) {
            try {
                // 特别注意循环控制变量i的设计，避免造成无限循环
                System.out.println(greetings[i++]);
     //             System.out.println (greetings[i]);
                /**
                 * 这样设计控制变量的增长会造成程序死循环，i一直等于3，然后本行代码一直执行不到，
                 * 因为上面一行代码在i=3的时候一直抛出了运行时异常，导致循环条件一直为真
                 */
     //             i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("数组下标越界异常");
            } finally {
                System.out.println("------这里内容会执行-----");
            }
        }
    }

    void method1() throws IOException {}  //合法

    //编译错误，必须捕获或声明抛出IOException
    void method2(){
    //    method1();
    }

    //合法，声明抛出IOException
    void method3()throws IOException {
        method1();
    }

    //合法，声明抛出Exception，IOException是Exception的子类
    void method4()throws Exception {
        method1();
    }

    //合法，捕获IOException
    void method5(){
        try{
            method1();
        }catch(IOException e){
          //  …
        }
    }

    //编译错误，必须捕获或声明抛出Exception
    void method6(){
        try{
            method1();
        }catch(IOException e){
     //       throw new Exception();
        }
    }

    //合法，声明抛出Exception
    void method7()throws Exception{
        try{
            method1();
        }catch(IOException e){
            throw new Exception();
        }
    }

    void method8(){
        try{
            int a = 6;
            int b = 0;
            int c = a / b;
        } catch (ArithmeticException e) {
            e.printStackTrace();
            throw e;  // 注意这里抛出的是一个运行时异常（算术异常），运行不做任何处理，编译也能通过
        }
    }

    void method9(){
        try{
           new FileInputStream("abc.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            /**
             * 下面这行代码不注释掉，编译不通过，抛出的为一个可检查异常，需要继续处理
             * 要么try..catch   要么方法签名处抛出
             */
     //       throw e;
        }
    }





}
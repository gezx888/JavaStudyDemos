package moduleexercise.reflect.proxydemo;

import moduleexercise.reflect.domain.Person;

/**
 *      本包下面写一些 反射在 动态代理 & 用于工厂模式的一些例子。    同时完成一个 Jdk的动态代理例子
 *
 * @description:    演示  如何获取类加载器 ？
 *                  1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。 
 *                  2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类 
 *                  3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
 * @author: gezx
 * @date: 2021/5/30 21:56
 */
public class GetClassLoaderDemo {
    public static void main(String[] args) {
        Person person = new Person();

        System.out.println(" 类加载器： " + person.getClass().getClassLoader().getClass().getName());
    }
}
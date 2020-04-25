package javaBase.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Describe: 反射测试类
 * @Author: Gezx
 * @Date: 2020/3/29 11:54
 */
public class ReflectTest {
    /**
     * 反射机制获取类三种方法:
     * （1）Object-->getClass
     * （2）任何数据类型（包括基本的数据类型）都有一个“静态”的class属性
     * （3）通过class类的静态方法：forName(String className)（最常用）
     */
    @Test
    public void testGetClass() throws ClassNotFoundException {
        // 第一种方式获取Class对象
        Student stu = new Student();  // 这一new将产生一个Student对象，一个Class对象（并且调用了相应的的构造方法）
        Class stuClass = stu.getClass();  // 获取Class对象
        System.out.println(stuClass.getName());
        System.out.println(stuClass.getSimpleName());

        // 第二种方式获取Class对象
        Class<Student> studentClass = Student.class;
        System.out.println(studentClass.getName());

        // 第三种方式获取Class对象
        Class<?> aClass = Class.forName("javaBase.reflect.Student"); // 此字符串参数为全限定名，真实路径，带包名
        System.out.println(aClass.getName());
        System.out.println(stuClass == studentClass);
        System.out.println(studentClass == aClass);
    }

    /**
     * 通过反射来生成对象主要有两种方法：
     */
    @Test
    public void testGetInstance(){
        // 方式一：使用Class对象的newInstance()方法来创建Class对象对应类的实例。
        Class<String> stringClass = String.class;
        try {
            Object str = stringClass.newInstance();
            Class aClass = str.getClass();  // 获得了一个实例
            System.out.println(aClass.getSimpleName());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        /** 方式二：
         * 先通过Class对象获取指定的Constructor对象，再调用Constructor对象的newInstance()方法
         * 来创建对象，这种方法可以用指定的构造器构造类的实例。
         */
        try {
            Class<?> aClass = Class.forName("java.lang.String");
            Constructor<?> constructor = aClass.getConstructor(String.class);
            String str = (String)constructor.newInstance("Hello world!");
            System.out.println(str);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}

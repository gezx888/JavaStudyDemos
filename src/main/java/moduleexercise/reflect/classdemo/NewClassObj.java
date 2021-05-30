package moduleexercise.reflect.classdemo;

import moduleexercise.reflect.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @description:        通过class调用其他类的构造函数（也可以通过这种方式通过Class创建其他类的对象）
 *                             主要演示 Constructor<?>[] con = demo.getConstructors(); 方法使用
 *                             获得全部修饰符为 public 的构造方法，修饰符为 private的获取不到
 * @author: gezx
 * @date: 2021/5/30 16:48
 */
public class NewClassObj {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Person per1 = null;
        Person per2 = null;
        Person per3 = null;
        Person per4 = null;

        // 获得全部修饰符为 public 的构造方法，修饰符为 private的获取不到
        Constructor<?>[] con = demo.getConstructors();
        try {
            per1 = (Person) con[3].newInstance();                       // 一般正式代码不会通过这样直接获取数组元素，都是通过for循环迭代
            per2 = (Person)con[2].newInstance("李四");
            per3 = (Person)con[1].newInstance(29);
            per4 = (Person)con[0].newInstance("张三",30);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);

    }
}

package moduleexercise.reflect.classdemo;

import java.lang.reflect.Constructor;

/**
 * @description:    获得其他类中的构造函数相关的  api
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class GetConstructors {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.OtherPerson");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取公共的即public的构造方法数组对象
        Constructor<?>[] cons = demo.getConstructors();     //复数形式
        for (int i = 0; i < cons.length; i++) {
            System.out.println("构造方法：  "+cons[i]);
        }

        System.out.println("===============================");

        // 获取某一个指定的公共的即public的构造方法
        try {
            Constructor<?> constructor = demo.getConstructor(int.class);
//            Constructor<?> constructor = demo.getConstructor(java.lang.String.class,int.class);          //报错 因为对应的构造方法为private修饰
            System.out.println(constructor.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("===============================");

        // 获取所有的构造方法数组对象
        Constructor<?>[] declaredConstructors = demo.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            System.out.println("全部构造方法：  " + declaredConstructors[i]);
        }

        System.out.println("===============================");

        // 获取某一个指定的构造方法
        try {
            Constructor<?> declaredConstructor = demo.getDeclaredConstructor(java.lang.String.class,int.class);
            System.out.println(declaredConstructor.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}

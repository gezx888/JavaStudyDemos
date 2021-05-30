package moduleexercise.reflect.methoddemo;

import java.lang.reflect.Method;

/**
 * @description:    反射获得某个类中指定的方法 method  并进行反射调用
 *                     主要看以下面的这些api的使用：
 *                      Method method = demo.getMethod("sayChina",java.lang.String.class);
 *                      method.invoke(demo.newInstance(),"china");       // 反射调用
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class InvokeMethod {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取指定的方法 method 对象 —— 并进行反射调用
        try {
            Method method = demo.getMethod("sayChina",java.lang.String.class);
            method.invoke(demo.newInstance(),"china");

            Method sayHello = demo.getMethod("sayHello", String.class, int.class);
            sayHello.invoke(demo.newInstance(),"张无忌",23);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

package moduleexercise.reflect.classdemo;

import moduleexercise.reflect.domain.Person;

/**
 * @description:    通过Class实例化其他类的对象
 *                      所以大家以后再编写使用Class实例化其他类的对象的时候，一定要自己定义无参的构造函数
 * @author: gezx
 * @date: 2021/5/30 16:32
 */
public class NewClassObject {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Person person = null;
        try {
            person = (Person) demo.newInstance();    //注意一下，当把Person中的默认的无参构造函数取消的时候，比如自己只定义一个有参数的构造函数之后，会出现错误：Caused by: java.lang.NoSuchMethodException: moduleexercise.reflect.domain.Person.<init>()
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        person.setAge(10);
        person.setName("张三");

        System.out.println(person);
    }
}

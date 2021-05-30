package moduleexercise.reflect.classdemo;

import moduleexercise.reflect.domain.Person;

/**
 * @description:       演示获取Class类对象的三种方法
 * @author: gezx
 * @date: 2021/5/30 16:05
 */
public class GetClassInstance {
    public static void main(String[] args) {
        Class<?> demo1 = null;
        Class<?> demo2 = null;
        Class<?> demo3 = null;

        try {
            // 一般尽量采用这种形式 —— 但是需要传入的是类的全限定名
            demo1 = Class.forName("moduleexercise.reflect.domain.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        demo2 = new Person().getClass();

        demo3 = Person.class;   // 每一种类型都有class属性（包括基本类型也有）

        System.out.println("类名称   "+demo1.getName());
        System.out.println("类名称   "+demo2.getName());
        System.out.println("类名称   "+demo3.getName());

        System.out.println(demo1==demo2);      //true 说明都是对应的 在堆区创建的描述moduleexercise.reflect.domain.Person这个类的同一个Class对象。
        System.out.println(demo2==demo3);      //true
    }
}

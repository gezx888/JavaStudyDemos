package moduleexercise.reflect.classdemo;

import moduleexercise.reflect.domain.Person;

/**
 * @description:       演示通过反射获得一个对象的完整的包名和类名
 *                  添加一句：所有类的对象其实都是Class的实例
 * @author: gezx
 * @date: 2021/5/30 15:56
 */
public class GetPackageClassName {

    public static void main(String[] args) {
        Person person = new Person();           // 平时框架开发项目过程中已经不通过这种手动new对象的形式创建对象了；而是通过Spring容器管理注入对象
        Class<? extends Person> personClass = person.getClass();

        System.out.println(personClass.getPackage());
        System.out.println(personClass.getName());
        System.out.println(personClass.getSimpleName());
    }
}

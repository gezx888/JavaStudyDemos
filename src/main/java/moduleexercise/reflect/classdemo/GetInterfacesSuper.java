package moduleexercise.reflect.classdemo;

/**
 * @description:    返回一个类实现的接口 && 继承的父类 api
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class GetInterfacesSuper {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<?>[] interfaces = demo.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i].getName());
        }

        Class<?> superclass = demo.getSuperclass();
        System.out.println("继承的父类为：   " + superclass.getName());
    }
}

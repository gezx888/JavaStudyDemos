package moduleexercise.reflect.fieldsdemo;

import java.lang.reflect.Field;

/**
 * @description:    通过反射操作某个类的属性
 *                      相关api如下：
 *
 * @author: gezx
 * @date: 2021/5/30 21:16
 */
public class OperateFields {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> demo = null;
        Object obj = null;

        demo = Class.forName("moduleexercise.reflect.domain.Person");
        obj = demo.newInstance();

        // 反射获取类中 指定的属性 对象。
//        Field field = demo.getDeclaredField("name");
        Field field = demo.getDeclaredField("sex");

        field.setAccessible(true);      // 当获取的属性是由private修饰的时候，需要调用这个

        field.set(obj,"男");
        System.out.println(field.get(obj));
    }
}

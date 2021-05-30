package moduleexercise.reflect.fieldsdemo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *          本包 fieldsdemo下面写一些 java.lang.reflect.Field 相关的 api 方法
 *
 * @description:    通过反射获取一个类中所有的属性 fields 相关的  api  包括修饰符  什么的一起输出来
 *                     主要看以下面的这些api的使用：
 *                      Field[] fi = demo.getFields();
 *                      Field[] fields = demo.getDeclaredFields();
 *                      int mo = fields[i].getModifiers();
 *                      String pri = Modifier.toString(mo);    修饰符类中的静态方法
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class GetTheFullFields {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取本类里面所有的属性 —— 包括private修饰符修饰的属性。
        System.out.println(" ===============本类属性======================== ");
        Field[] fields = demo.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            // 权限修饰符
            int mo = fields[i].getModifiers();
            String pri = Modifier.toString(mo);
            // 属性的类型
            Class<?> type = fields[i].getType();

            System.out.println(pri + " " + type.getSimpleName() + " " + fields[i].getName() + ";");
        }

        // 获取所有public修饰符修饰的属性，包括 实现的接口或者继承的父类中的public修饰的属性
        System.out.println(" ===============包括实现的接口或者继承的父类的属性==================== ");
        Field[] fi = demo.getFields();
        for (int i = 0; i < fi.length; i++) {
            // 权限修饰符
            int mo = fi[i].getModifiers();
            String pri = Modifier.toString(mo);
            // 属性的类型
            Class<?> type = fi[i].getType();

            System.out.println(pri + " " + type.getSimpleName() + " " + fi[i].getName() + ";");
        }


    }
}

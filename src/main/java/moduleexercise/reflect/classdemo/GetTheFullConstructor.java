package moduleexercise.reflect.classdemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * @description:    获得一个类中的完整的构造函数相关的  api  包括修饰符 什么的一起输出来
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class GetTheFullConstructor {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.OtherPerson");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取所有的构造方法数组对象
        Constructor<?>[] cons = demo.getDeclaredConstructors();
//        for (int i = 0; i < cons.length; i++) {
//            Class<?>[] types = cons[i].getParameterTypes();
//            int mo = cons[i].getModifiers();           // 获取方法修饰符api，返回值为int类型，需要借助Modifier类的toString静态方法完成转换
//            // 开始拼构造方法，当然也可以借助StringBuilder等对象拼接类
//            System.out.print("构造方法：  ");
//            System.out.print(Modifier.toString(mo) + " ");
//            System.out.print(cons[i].getName());
//            System.out.print("(");
//            for (int j = 0; j < types.length; j++) {
//                System.out.print(types[j].getName() + " arg" + j);
//                if(j<types.length-1){
//                    System.out.print(",");
//                }
//            }
//            System.out.println("){}");
//        }

        for (int i = 0; i < cons.length; i++) {
            Class<?>[] types = cons[i].getParameterTypes();
            int mo = cons[i].getModifiers();
            // 开始拼构造方法，当然也可以借助StringBuilder等对象拼接类
            StringBuilder str = new StringBuilder();
            str.append("构造方法：  ");
            str.append(Modifier.toString(mo)).append(" ");
            str.append(cons[i].getName());
            str.append("(");
            for (int j = 0; j < types.length; j++) {
                str.append(types[j].getName()).append(" arg").append(j);
                if(j<types.length-1){
                    str.append(",");
                }
            }
            str.append("){}");
            System.out.println(str);
        }

    }
}

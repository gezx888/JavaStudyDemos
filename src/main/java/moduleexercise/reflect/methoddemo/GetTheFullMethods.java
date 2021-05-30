package moduleexercise.reflect.methoddemo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *              本包methoddemo下面写一些 java.lang.reflect.Method 相关的 api 方法
 *
 * @description:    获得一个类中所有的完整的方法 method 相关的  api  包括修饰符、抛出异常 什么的一起输出来
 *                     主要看以下面的这些api的使用：
 *                      Method[] method = demo.getMethods();
 *                      Class<?> returnType = method[i].getReturnType();
 *                      Class<?>[] para = method[i].getParameterTypes();
 *                      Class<?>[] exce = method[i].getExceptionTypes();
 *
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class GetTheFullMethods {
    public static void main(String[] args) {
        Class<?> demo = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 获取所有的方法数组对象 —— 只能获取 所有 public修饰符修饰的方法，并且可以获取到从父类继承过来的public方法
        Method[] method = demo.getMethods();
        for (int i = 0; i < method.length; i++) {
            Class<?> returnType = method[i].getReturnType();
            Class<?>[] para = method[i].getParameterTypes();
            int mo = method[i].getModifiers();
            // 开始拼方法
            StringBuilder str = new StringBuilder();
            str.append("方法：  ");
            str.append(Modifier.toString(mo)).append(" ");
            str.append(returnType.getSimpleName()).append(" ");
            str.append(method[i].getName()).append(" ");
            str.append("(");
            for (int j = 0; j < para.length; j++) {
                str.append(para[j].getName()).append(" arg").append(j);
                if(j<para.length-1){
                    str.append(",");
                }
            }
            Class<?>[] exce = method[i].getExceptionTypes();
            if(exce.length>0){
                str.append(") throws");
                for (int j = 0; j < exce.length; j++) {
                    str.append(exce[j].getName()).append(" ");
                    if(j<exce.length-1){
                        str.append(",");
                    }
                }
            }else {
                str.append(")");
            }
            System.out.println(str);
        }

    }
}

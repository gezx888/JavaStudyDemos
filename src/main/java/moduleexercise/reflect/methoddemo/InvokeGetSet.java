package moduleexercise.reflect.methoddemo;

import java.lang.reflect.Method;

/**
 * @description:    反射调用某个类中的 get set方法  进行反射调用
 *                     主要看以下面的这些api的使用：
 *                      Method[] method = demo.getMethods();
 *
 * @author: gezx
 * @date: 2021/5/30 17:27
 */
public class InvokeGetSet {
    public static void main(String[] args) {
        Class<?> demo = null;
        Object obj = null;

        try {
            demo = Class.forName("moduleexercise.reflect.domain.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            obj = demo.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        setter(obj,"Sex","男",String.class);
        getter(obj,"Sex");
    }

    /**
     * @param obj  操作的对象
     * @param attr  操作的属性
     */
    public static void getter(Object obj,String attr){
        try {
            Method method = obj.getClass().getMethod("get" + attr);

            System.out.println(method.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj  操作的对象
     * @param attr   操作的属性
     * @param value     设置的值
     * @param type    参数的类型
     */
    public static void setter(Object obj,String attr,Object value,Class<?> type){
        try {
            Method method = obj.getClass().getMethod("set" + attr,type);

            method.invoke(obj,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

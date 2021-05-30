package moduleexercise.reflect.fieldsdemo;

import java.lang.reflect.Array;

/**
 * @description:    通过反射取得并修改数组信息
 * @author: gezx
 * @date: 2021/5/30 21:26
 */
public class InvokeArray {
    public static void main(String[] args) {
        int[] temp = {1,2,3,4,5};

        Class<?> type = temp.getClass().getComponentType();

        System.out.println("数组类型: " + type.getName());
        System.out.println("数组长度: " + Array.getLength(temp));
        System.out.println("数组的第一个元素为: " + Array.get(temp,0));

        Array.set(temp,0,100);
        System.out.println("修改之后数组第一个元素为： " + Array.get(temp, 0));
    }
}

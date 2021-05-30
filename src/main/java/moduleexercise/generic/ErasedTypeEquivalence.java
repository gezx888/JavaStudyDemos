package moduleexercise.generic;

import java.util.ArrayList;

/**
 * Java 的泛型使用了类型擦除机制，这个引来了很大的争议，以至于 Java 的泛型功能受到限制，只能说是”伪泛型“。
 * 什么叫类型擦除呢？简单的说就是，类型参数只存在于编译期，在运行时，Java 的虚拟机 ( JVM ) 并不知道泛型的存在
 *
 * @description:      下面的代码有两个不同的 ArrayList：ArrayList<Integer> 和 ArrayList<String>。
 *                    在我们看来它们的参数化类型不同，一个保存整性，一个保存字符串。但是通过比较它们的 Class 对象，
 *                    下面的代码输出是 true。这说明在 JVM 看来它们是同一个类
 * @author: gezx
 * @date: 2021/5/31 0:26
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1==c2);
    }
}

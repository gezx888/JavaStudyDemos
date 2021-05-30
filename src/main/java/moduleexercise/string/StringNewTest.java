package moduleexercise.string;

/**
 * @description:        主要演示通过 = 号直接赋值字符串常量 与 new关键字新建字符串常量的区别
 *                  首先明白一个事，java存在一个常量池，可以用来存储字符串常量。
 * @author: gezx
 * @date: 2021/5/23 23:14
 */
public class StringNewTest {
    public static void main(String[] args) {
        String a = "aaa";
        String b = "aaa";
        String c = new String("aaa");
        System.out.println("a==b:" + (a==b));
        System.out.println("a==c:" + (a==c));
        System.out.println("a与b的值相等:"+(a.equals(c)));
    }
}

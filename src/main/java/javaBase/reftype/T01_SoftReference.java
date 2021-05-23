package javaBase.reftype;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @description:        主要演示Java四种引用类型之一的软引用类型的特点 —— 当内存空间不足的时候，软引用会被回收
 *                      从源码可以看出软引用对象类 SoftReference 是一个带有泛型参数的泛型类
 *
 * @author: gezx
 * @date: 2021/5/23 21:20
 */
public class T01_SoftReference {
    public static void main(String[] args) {
        // 内存结构关系为：obj 通过一个强引用 指向创建出的 SoftReference 对象；然后通过一个 软引用 指向新建出的new byte[1024*1024*10] 对象。
        SoftReference<byte[]> obj = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(obj.get());
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(obj.get());

        // 通过改变设置该类运行时候的堆内存大小，并且再分配一个数组，使得heap将装不小，这时候系统会垃圾回收，内存空间不足时会把软引用干掉回收掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(obj.get());
    }
}

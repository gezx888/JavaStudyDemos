package javaBase.reftype;

import java.lang.ref.WeakReference;

/**
 * @description:      演示弱引用对象的使用以及特点：弱引用与软引用的区别在于：
 * 只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，
 * 不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
 * @author: gezx
 * @date: 2021/5/23 21:52
 */
public class T01_WeakReference {
    public static void main(String[] args) {
        // 内存结构关系为：m 通过一个强引用 指向创建出的 WeakReference 对象；然后通过一个 弱引用 指向新建出的new M() 对象。
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();            // 当程序确定对象M不被再需要使用的时候，需要调用ThreadLocal对象的remove方法，这样来防止内存泄露
    }

    static class M{
        public M(){
            System.out.println("您好");
        }
    }
}

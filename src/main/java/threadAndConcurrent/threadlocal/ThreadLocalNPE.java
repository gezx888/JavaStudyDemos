package threadAndConcurrent.threadlocal;

/**
 * @className: ThreadLocalNPE
 * @description:  该类主要用来演示 ThreadLocal 的空指针相关问题，其实是 装箱拆箱 转换过程中出现的空指针问题
 * @author: gezx
 * @date: 2021/2/19
 **/
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public Long get(){
        return longThreadLocal.get();  // 返回值类型为long的时候会出现空指针问题
    }

    public static void main(String[] args) {

        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());   // 打印 null 看源码可以分析出打印 null

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread.start();
    }

}

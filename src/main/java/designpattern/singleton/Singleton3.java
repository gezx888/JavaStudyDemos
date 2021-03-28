package designpattern.singleton;

/**
 * @className: Singletons
 * @description:            该类使用 双重校验锁 的模式完成单例模式的创建 —— 双重校验锁 （线程安全）
 * @author: gezx
 * @date: 2021/3/25
 **/
public class Singleton3 {
    private static volatile Singleton3 instance = null;

    private Singleton3(){

    }

    /**
            当第一次调用 getInstance() 方法的时候 instance 为空，则进行同步操作，保证多线程实例唯一
        当第一次之后 再次 getInstance() 方法的时候 instance 不为空，则就不再进入同步代码块了，减少不不要的同步，提高性能！
     */
    public static Singleton3 getInstance(){
        if(instance == null){
            synchronized(Singleton3.class){
                if(instance == null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

}

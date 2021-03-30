package demos.singleton;

/**
 * @description:  懒汉式单例模式 —— 线程不安全 ( 可以通过加 volatile synchronized 关键字达到线程安全)
 * @author: gezx
 * @date: 2021/3/30 20:46
 */
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton(){ }

    public static synchronized Singleton getInstance(){
        if(instance == null){
            return new Singleton();
        }
        return instance;
    }
}

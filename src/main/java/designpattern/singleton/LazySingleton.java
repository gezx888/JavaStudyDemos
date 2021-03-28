package designpattern.singleton;

/**
 * @className: LazySingleton
 *                      单例模式：指一个类只有一个实例，且该类能够自行创建这个实例 的一种模式。
 *                      三个特点：（1）单例类只有一个实例对象 （2）该单利对象必须由单例类自行创建 （3）单例类对外提供一个访问该单例类的全局访问点
 *                      优点：单例模式可以保证内存里面只有一个实例，减少了内存开销；可以避免对资源的多重占用等
 *
 * @description:            该类写 懒汉式单例模式：类加载的时候并不会生成实例，而是等到第一次调用getInstance() 方法的时候才会去创建这个单例
 *
 *                      如果编写的是多线程程序，则不要删除下例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
 *                      如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
 * @author: gezx
 * @date: 2021/3/25
 **/
public class LazySingleton {
    private static volatile LazySingleton instance = null;      // volatile关键字保证instance在所有线程中同步

    private LazySingleton(){    // private 避免类在外部被实例化

    }

    public static synchronized LazySingleton getInstance(){     // synchronized关键字保证线程安全
        if(instance == null){
            return new LazySingleton();
        }
        return instance;
    }
}

package designpattern.singleton;

/**
 * @className: HungrySingleton
 * @description:        演示单例模式的饿汉式写法：饿汉式单例的特点是一旦类被加载的时候就创建一个单例，保证在调用getInstance()方法之前单例已经存在
 * @author: gezx
 * @date: 2021/3/25
 **/
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();      // 用final关键字可以保证线程安全 即是一旦被创建就不可改变

    private HungrySingleton(){

    }

    public static HungrySingleton getInstance(){
        return instance;
    }
}

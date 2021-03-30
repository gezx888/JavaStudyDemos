package demos.singleton;

/**
 * @description:    饿汉式单例模式
 * @author: gezx
 * @date: 2021/3/30 20:52
 */
public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return instance;
    }
}

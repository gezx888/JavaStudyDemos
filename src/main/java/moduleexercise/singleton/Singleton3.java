package moduleexercise.singleton;

/**
 * @description:    双重校验锁型单例模式
 * @author: gezx
 * @date: 2021/3/30 20:54
 */
public class Singleton3 {
    private static volatile Singleton3 instance = null;

    private Singleton3(){}

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

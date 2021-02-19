package threadAndConcurrent.threadlocal;

/**
 * @className: ThreadLocalNormalUsage06
 * @description:   该类主要用来演示 ThreadLocal 的常见的第二种使用场景：避免同一个请求中的公共参数信息在各个业务处理类中层层传递的麻烦
 * @author: gezx
 * @date: 2021/2/19
 **/
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        Service1 service1 = new Service1();
        service1.process("宝强");
    }

}


// 模拟业务处理类
class Service1{

    public void process(String name){
        User user = new User(name);
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);

        UserContextHolder.holder.remove();  // 清除上下文持有对象中的参数信息，并且可以重新赋值 —— 注意SpringMvc框架中相关对象 RequestContextHolder 的使用

        User user2 = new User("王姐");
        UserContextHolder.holder.set(user2);

        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
    }
}


// 这个内部类模拟参数信息的持有类
class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User{

    String name;

    public User(String name) {
        this.name = name;
    }
}

package thread;

/**
 * @description: 这个类主要用来模拟多个用户共享同一个账号，取钱时候没有采取同步机制时候产生的数据安全问题
 * @author: gezx
 * @date: 2020/4/20 23:20
 */
public class ThreadDemo3 {
    public static void main(String[] args) {

    }
}

 class Account implements Runnable{
    private String num;
    private double blance;
    Account(){

    }


    @Override
    public void run() {

    }
}

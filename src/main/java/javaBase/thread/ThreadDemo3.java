package javaBase.thread;

/**
 * @description: 这个类主要用来模拟多个用户(线程)共享同一个账号，取钱时候没有采取同步机制时候产生的数据安全问题
 * @author: gezx
 * @date: 2020/4/20 23:20
 */
public class ThreadDemo3 {
    public static void main(String[] args){
        // 下面这样就会共享一个数据账户了
        Account account = new Account("No1", 5000);
        Thread thread1 = new Thread(new Processor(account));
        thread1.setName("t1");
        thread1.start();

        Thread thread2 = new Thread(new Processor(account));
        thread2.setName("t2");
        thread2.start();
    }
}

class Processor implements Runnable{
    // 有这样一个属性目的是营造一个共享数据账户
    private Account account;
    Processor(Account account){
        this.account = account;
    }
    // 线程任务取款
    @Override
    public void run() {
        try {
            account.withdraw(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程 " + Thread.currentThread().getName() + "取款成功，余额为： " + account.getBlance());
    }
}


 class Account{
    private String num;
    private double blance;

    Account(String num,double blance){
        this.num = num;
        this.blance = blance;
    }
    public String getNum(){
        return num;
    }
    public void setNum(String num){
        this.num = num;
    }
     public double getBlance(){
         return blance;
     }
     public void setBlance(double blance){
         this.blance = blance;
     }

    /* public void withdraw(double money) throws InterruptedException {
         double after = this.blance - money; // 暂时不考虑余额不足问题
         Thread.sleep(1000);
         setBlance(after);
     }*/

     /**
      * 下面这样的代码采用了同步机制，保证了在多线程环境下对共享数据需要进行修改的时候的数据安全问题，
      * 但这样将牺牲掉效率
      * @param money
      * @throws InterruptedException
      */
      public void withdraw(double money) throws InterruptedException {
         // 注意下面的语句块的锁对象一定是共享对象，这里是数据账户，可以使用this
         synchronized(this){
             double after = this.blance - money; // 暂时不考虑余额不足问题
             Thread.sleep(1000);
             setBlance(after);
         }
     }


}

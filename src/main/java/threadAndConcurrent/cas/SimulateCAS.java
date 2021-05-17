package threadAndConcurrent.cas;

/**
 * @description:    本类主要用来模拟 CAS 思想的等价代码demo —— 也即是下面的整个代码相当于 CAS 一条指令完成的事情
 * @author: gezx            CAS 指令是原子性的操作，即要么都成功，要么都失败 （所以本类的下面的方法通过 synchronized 锁来保证）
 *                          内存值等于 期望值，则更新成功，返回更新后的值，内存值不等于期望值，则什么也不做
 *                          利用 CAS 思想 + 自旋 的动作可以实现乐观锁
 * @date: 2021/5/1 11:50
 */
public class SimulateCAS {
    private volatile int value;         // 通过volatile关键字来保证可见性

    public synchronized int compareAndSwap(int expectValue,int newValue){
        int oldValue =  value;
        if(oldValue == expectValue){
            value = newValue;
        }
        return oldValue;
    }
}

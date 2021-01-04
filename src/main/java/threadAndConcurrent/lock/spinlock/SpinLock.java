package threadAndConcurrent.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *   阻塞和唤醒线程都是需要高昂的开销的，如果同步代码块中的内容不复杂，那么可能转换线程带来的开销比实际业务代码执行的开销还要大。
 *   用一句话总结自旋锁的好处，那就是自旋锁用循环去不停地尝试获取锁，让线程始终处于 Runnable 状态，节省了线程状态切换带来的开销。
 *
 * 该类主要演示自旋锁的相关用法： 自己按照自旋锁的原理，自己可以写一个具有自旋功能的锁，里面包含上锁以及解锁的两个方法
 *      主要用到了 AtomicReference类里面的 compareAndSet 方法结合while循环进行一个获锁等待的过程
 *
 *      从该程序也可以看出，自旋锁有时候的确可以提高效率，但有时候自旋的时间太长（对方压根不想释放锁的情况下）这样也会降低效率
 *
 *      它最大的缺点就在于虽然避免了线程切换的开销，但是它在避免线程切换开销的同时也带来了新的开销，因为它需要不停得去尝试获取锁。
 *      如果这把锁一直不能被释放，那么这种尝试只是无用的尝试，会白白浪费处理器资源。也就是说，虽然一开始自旋锁的开销低于线程切换，
 *      但是随着时间的增加，这种开销也是水涨船高，后期甚至会超过线程切换的开销，得不偿失。
 *
 *      所以自旋锁适用于：自旋锁适用于并发度不是特别高的场景，以及临界区比较短小的情况，这样我们可以利用避免线程切换来提高效率。
 *      可是如果临界区很大，线程一旦拿到锁，很久才会释放的话，那就不合适用自旋锁，因为自旋会一直占用 CPU 却无法拿到锁，白白消耗资源。
 *
 *
 * @author gezx  自己实现一个自旋锁 （可重入的）
 * @date 2021/1/4 17:18
 */
public class SpinLock {
    private AtomicReference<Thread> spinLock = new AtomicReference<>();

    private int count=0;  // 重入次数

    public void lock(){
        Thread thread = Thread.currentThread();

        if(thread == spinLock.get()){
            ++count;
            return;
        }

        while (!spinLock.compareAndSet(null,thread)){
            System.out.println("自旋获取失败，再次尝试");
        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();

        if(thread == spinLock.get()){
            if(count>0){
                --count;
            }else {
                spinLock.set(null);
            }
        }

    //    spinLock.compareAndSet(thread,null);   这是慕课网课程原版 上面是改造版本 可重入的自旋锁

    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始尝试申请获取自旋锁");
                spinLock.lock();
                System.out.println(Thread.currentThread().getName()+"已经获取到了自旋锁");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock();
                    System.out.println(Thread.currentThread().getName() + "释放了自旋锁");
                }
            }
        };

        Thread thread1 = new Thread(runnable,"thread1");
        Thread thread2 = new Thread(runnable,"thread2");
        thread1.start();
        thread2.start();
    }
}

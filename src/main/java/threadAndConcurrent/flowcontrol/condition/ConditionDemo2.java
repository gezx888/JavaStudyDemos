package threadAndConcurrent.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: ConditionDemo2
 * @description:  演示condition类的另一种用法：用于生产者/消费者模型中：即是会有两个条件，消费者只有等到队列中有元素的时候即不为空的时候才可以去消费
 *                  而生产者只有等到队列中有空位置不为满的时候才可去生产
 * @author: gezx
 * @date: 2021/2/19
 **/
public class ConditionDemo2 {

    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();  // 队列满的条件
    private Condition notEmpty = lock.newCondition();  // 队列空的条件

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Consumer consumer = conditionDemo2.new Consumer();
        Producer producer = conditionDemo2.new Producer();
        consumer.start();
        producer.start();
    }

    class Consumer extends Thread{

        @Override
        public void run() {
            consume();
        }

        private void consume(){
            while(true){
                lock.lock();
                try {
                    while(queue.size()==0){
                        System.out.println("队列为空，需要等待数据");
                        notEmpty.await();    // 消费者需要等待队列没有为空的条件再继续消费
                    }
                    // 跳出while循环了说明队列里面已经有数据了，可以进行消费了
                    queue.poll();
                    notFull.signalAll();     // 同时这个时候有空位了需要唤醒生产者进行生产了
                    System.out.println("从队列里面取走了一个数据，队列剩余元素个数为：" + queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == queueSize){
                        System.out.println("队列位置已满了，需要等待空位置再生产");
                        notFull.await();     // 生产者需要等待队列位置没有满的条件再继续生产
                    }
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("向队列插入了一个元素，队列剩余空间" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

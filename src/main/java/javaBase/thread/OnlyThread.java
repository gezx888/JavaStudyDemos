package javaBase.thread;


/**
 * @description: 这是一个单线程只有一个线程的演示例子
 * @author: gezx
 * @date: 2020/4/20 22:03
 */
public class OnlyThread {
    public static void main(String[] args) {
        run();
    }
    private static void run() {
        for(int count=1,row=1;row<10;count++,row++){ // 外层循环控制行数
            for(int i=0;i<count;i++){  // 内层循环控制输出*个数，输出count个*
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

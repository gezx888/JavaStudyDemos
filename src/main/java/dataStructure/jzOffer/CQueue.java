package dataStructure.jzOffer;

import java.util.Stack;

/**
 * 剑指offer 面试题9：用两个栈来实现队列的进队、出队操作
 * 思路：队列的特点是：先进队元素先出队，后进后出原则
 * 二个栈：一个主栈，用来添加元素，可以实现入队列；另外一个栈，辅助栈，主要用来删除元素
 *   （删除元素出队列时，需要将第一个栈中的元素逆转过来：这样才能起到先进元素先出队）
 * @author gezx
 * @date 2020/6/12 14:56
 */
public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public CQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    // 直接进栈 模拟入队列操作
    public void appendTail(int value){
        stack1.push(value);
    }
    // 通过辅助栈2 主要来删除元素，模拟出队操作，栈2里面有元素直接弹栈返回，如果为空了，先将栈1中的元素逆转过来，再弹栈
    public int deleteHead(){
        if(stack1.isEmpty() && stack2.isEmpty()){
            return -1;
        }
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}

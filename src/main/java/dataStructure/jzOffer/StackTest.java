package dataStructure.jzOffer;

import java.util.Stack;

/**
 * @description: 该类写的是剑指offer与 栈 相关的一些面试题
 * @author: gezx
 * @date: 2020/6/13 23:46
 */
public class StackTest {

    /**
            剑指offer case21： 栈的压入、弹出序列：输入一个压入序列，一个弹出序列，判断弹出序列是否可能为压入序列的一个出栈顺序（两个序列的长度相等）
     * @description:  解题思路：新建一个辅助栈，循环遍历 将压入序列数组A 压入栈中，当栈顶元素等于弹出序列数组B的元素时候，就将其出栈，同时弹出序列位置后移
     *                          当循环结束，判断栈是否为空，若为空则返回true
     * @return:
     * @author: gezx
     * @date: 2021/3/9
     */
    public boolean isPopOrder(int[] pushA,int[] popB){
        if(pushA.length ==0 || popB.length == 0 || pushA.length != popB.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while(!stack.isEmpty() && stack.peek() == popB[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }




}

/**
 * @description:  练习剑指offer case-5：用两个栈来实现队列的 入队push 和 出队pop方法 操作
 *              分析：队列数据是按照先进先出原则。所以入队push的话只需要用一个栈就可以
 *                  出队列需要保证先进队列的先出来，而第一个栈的数据先进的全部在栈底，所以这样的话需要再增加一个栈，将第一个栈先进的数据全部弹栈到第二个栈里面
 *                  出队列pop通过第二个栈弹栈（只要第二个栈不为空）
 *
 *              根据上述栗子可得出结论：
 *              （1）当插入时，直接插入 stack1
 *              （2）当弹出时，当 stack2 不为空，弹出 stack2 栈顶元素，如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素
 *
 * @author: gezx
 * @date: 2021/3/8
 */
class Solution1{
    Stack<Integer> stack1 =  new Stack<Integer>();
    Stack<Integer> stack2 =  new Stack<Integer>();

    // 入队
    public void push(int node){
        stack1.push(node);
    }

    // 出队
    public int pop(){
        if(stack2.size() <= 0){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}

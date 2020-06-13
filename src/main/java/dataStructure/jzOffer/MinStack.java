package dataStructure.jzOffer;

/**
 * @description: 该类写的是剑指offer面试题 case30：包含min函数（最小值函数）的自定义栈
 * @author: gezx
 * @date: 2020/6/13 23:47
 */

import java.util.Stack;

/**
 * 分析：普通栈的 压栈方法push() 弹栈方法pop() 的时间复杂度为O(1) 但是最小值函数需要遍历栈 时间复杂度为O(N)
 * 所以本题难度为将最小值函数的时间复杂度降为O(1)
 *
 * 思路：通过辅助栈实现，建立两个栈：一个用于正常存储数据栈，实现正常的push、pop以及获取栈顶元素方法top
 * 另外一个为最小值栈，存储数据栈中的非严格降序数字
 * 所以本题重点就是维护好 最小值栈中的数据，以便于在取最小值函数时候的正确性！
 *
 * 总结上面：需要在两个地方维护好最小值栈中的数据
 * 1、push(x) 函数： 重点为保持最小值栈 的元素是 非严格降序 的。
 * 2、pop() 函数： 重点为保持数据栈, 与最小值栈的 元素一致性 。
 */

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    public MinStack(){
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x){
        stack.add(x);
        // 如果压栈元素比最小值栈的最小值小，那么也要将其压入最小值栈中
        if(minStack.isEmpty() || x<minStack.peek()){
            minStack.add(x);
        }
    }
    public void pop(){
        if(stack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        stack.pop();
    }
    public int top(){
        return stack.peek();
    }
    public int min(){
        return minStack.peek();
    }
}

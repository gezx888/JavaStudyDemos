package dataStructure.jzOffer;

import org.junit.Test;

import java.util.Stack;

/**
 * 该类写的是剑指offer与数据结构相关的一些面试题
 * @author gezx
 * @date 2020/6/12 13:58
 */
public class LinkListTest {
    // 链表内部节点类
    class LinkNode{
        int val;
        LinkNode next;
        LinkNode(int x){
            val = x;
        }
    }

    @Test
    public void test(){

        System.out.println();
    }

    /**
     * case6：从尾到头打印链表：利用数据结构 栈 的先进后出的特点，可以先将链表元素顺序倒置，然后将其弹栈到一个数组中返回即可
     * @param head
     * @return
     */
    private int[] reversePrint(LinkNode head){
        Stack<LinkNode> stack = new Stack<>();
        LinkNode temp = head;
        while(temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        int[] print = new int[stack.size()];
        for (int i = 0; i < print.length; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }


}

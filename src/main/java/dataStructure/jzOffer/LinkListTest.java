package dataStructure.jzOffer;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 该类写的是剑指offer与数据结构相关的一些面试题
 * @author gezx
 * @date 2020/6/12 13:58
 */
public class LinkListTest {
    // 链表内部节点类
    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
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
    private int[] reversePrint(ListNode head){
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
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

    /**
     * case18：删除链表的节点：
     *
     * 思路分两步走：1、定位节点：循环遍历链表，直到head.val==val的时候跳出，既可定位目标节点
     * 2、修改引用：假设当前节点cur 其前驱节点为 pre，后继节点为 cur.next，则执行pre.next = cur.next,即可以实现
     *      删除当前的cur节点
     *
     * @param head
     * @param val
     * @return
     */
    private ListNode deleteNode(ListNode head,int val){
        if(head.val==val){
            return head.next;
        }
        ListNode pre = head,cur = head.next;
        while(cur!=null && cur.val!=val){
            pre = cur;
            cur = cur.next;
        }
        // 当跳出循环时候cur不为空，说明定位到了目标节点，需要执行删除操作
        if(cur!=null){
            pre.next = cur.next;
        }
        return head;
    }

    /**
     * case22：求并且返回链表中倒数的第K个节点（我们约定最后一个节点为倒数第一个节点）
     *
     * 思路：1、最先想到 暴力统计解法，首先统计链表的长度n，然后初始化一个指向头结点的指针，并让它向前走
     *          n-k 步，这时候指针指向的便是倒数第 k 个节点
     *
     * 方法二：双指针法(不需要统计链表长度)：首先初始化两个都指向头结点的指针，然后让前指针former先向前走 k 步，
     *          然后再将两个指针一同向前走，每次向前移动一步，当前指针走过最后一个节点，即跳出链表的时候就已经走了
     *          n-k 步了，这个时候后指针也走了n-k步（此时距离尾节点为k-1步），此时后指针指向的便是倒数第k个节点
     *
     * @return
     */
    private ListNode getKthFromEnd(ListNode head,int k){
        ListNode former=head,latter=head;
        // 先让前指针向前走k步
        for (int i = 0; i < k; i++) {
            if(former==null)return null;   // 考虑特殊情况：k大于链表长度
            former = former.next;
        }
        while (former!=null){
            latter = latter.next;
            former = former.next;
        }
        return latter;
    }

    /**
     * case24：反转链表：涉及链表操作的题目一般先在纸上画出过程来，再写程序！
     *
     * 常规思路：利用外部空间，申请一个动态增长可扩容的数组或是容器 如ArrayList这种
     *          然后依次遍历链表，将链表中的元素增加到这个容器中，之后再利用容器自身的反转api执行，起到
     *          反转的效果，最后同时遍历链表和容器，将链表中的元素改成容器中的，再返回链表即可！
     *
     *  方法二：双指针迭代法：1、首先初始化两个指针，一个（cur）指向头结点head，另一个（pre）指向null；
     *                   2、然后通过cur依次遍历链表，每次迭代都将cur的next指向pre， 通过这样来实现局部反转，局部反转后cur pre分别同时前进一位；
     *                   循环，一直直至cur到达链表尾部。
     *                   3、重要：在每一次迭代的时候都需要通过一个临时temp节点在最开始保留当前节点cur的next节点！这样以达到丢失节点的目的，实现每一个链表节点的反转
     *
     *  方法三：递归解法（见leetCode官网题解）
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head){
        ListNode pre=null,cur=head;
        ListNode temp = null;
        while(cur!=null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * case25：合并两个排序的链表
     *
     * 方法一：由于两链表都是递增的，因此容易想到使用两个指针l1，l2分别遍历两个链表，根据l1.val 与 l2.val的大小关系确定两个节点的
     * 添加顺序，两个节点交替前进，直至遍历完毕。
     * 引入伪头节点，由于初始状态合并链表中无节点，因此循环第一轮时无法将节点添加到合并链表中。解决方案：初始化一个辅助节点 dum
     * 作为合并链表的伪头节点，将各节点添加至 dum 之后。
     * 算法步骤：1、初始化伪头结点dum， cur指向dum；
     *          2、根据大小循环合并；
     *          3、合并剩余链表部分
     *          4、返回合并链表头结点，即dum.next。
     *
     * 方法二：递归解法（可以复用其中一个链表）：首先根据两个链表的第一个节点值的大小确定合并链表的第一个节点，剩余下的按照递归
     * 解法进行求解。
     *
     * @param l1
     * @param l2
     * @return
     */
    // 方法一：
    private ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        while (l1!=null && l2!=null){
            if(l1.val<l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1!=null ? l1:l2;
        return dum.next;
    }

    // 递归解法：
    private ListNode mergeTwoLists2(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }else if(l1.val<l2.val){
            l1.next = mergeTwoLists2(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }

    /**
     * 剑指offer case46：圆圈中最后剩下的数
     *    思路一：约瑟夫环问题：使用数学公式递推法  重要点：（1）最后剩下的数的编号肯定为0；（2）递推公式：f(n,m) = (f(n-1,m) + m) % n;
     *
     *    思路二：链表模拟  每下一次删除的位置标号为：前一次的坐标位置 + 移动标尺 再求余得到  ps：当起始标号从0开始索引时，应该是加上 m-1
     */

    // 思路二：约瑟夫环问题
    public int LastRemaining_Solution(int n, int m) {
        if(n<1 || m<1){
            return -1;
        }
        int pos = 0;    //最终活下来的那个人的坐标位置肯定是会为0的
        for (int i = 2; i <= n; i++) {
            pos = (pos + m) % i;            // 递推公式
        }
        return pos;
    }

    // 思路二：链表模拟
    public int LastRemaining_Solution2(int n, int m) {
        if(n<1 || m<1){
            return -1;
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int p = 0;
        while(list.size()>1){
            p = (p + m-1) % list.size();
            list.remove(p);
        }
        return list.get(0);
    }


}

package dataStructure.jzOffer;

import java.util.*;

/**
 * @description:       该类写剑指offer上面的所有的算法题目！！！ 顺序按照牛客网上 剑指offer题目的顺序
 * @author: gezx
 * @date: 2021/3/30 20:45
 */
public class JzOfferExercise {

    static class ListNode{
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
        }
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    /**
     * 第一题：二维数组中的查找
     */
    public boolean Find(int target, int [][] array) {
        int i = 0;
        int j = array[0].length-1;
        int len = array.length;
        while(i<len && j>=0){
            if(array[i][j] < target){
                i++;
            }else if(array[i][j] > target){
                j--;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * 第二题：替换空格
     */
    public String replaceSpace (String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
        //或者直接使用下面的字符串api
//       return s.replaceAll(" ","%20");
    }

    /**
     * 第三题：从尾到头打印链表
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode node) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<ListNode> stack = new Stack<>();
        while(node != null){
            stack.push(node);
            node = node.next;
        }
        while(stack.size() > 0){
            ListNode nodeH = stack.pop();
            result.add(nodeH.val);
        }
        return result;
    }

    /**
     * 第四题：重建二叉树
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length==0 || in.length==0 || pre.length!=in.length ){    // 对参数序列进行判断
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);   // 建立树的根节点
        for (int i = 0; i < pre.length; i++) {
            if(pre[0] == in[i]){
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));    // 递归建立左子树
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));    // 递归建立右子树
                break;
            }
        }
        return root;
    }

    /**  第五题：用两个栈来实现队列
     *
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    // 入队
    public void push(int node){
        stack1.push(node);
    }
    // 出队：注意栈的特点是先进后出的，然而队列的特点是先进先出的，所以出队操作需要借助两个栈来实现
    public int pop(){
        if(stack2.size()<=0){
            while(stack1.size()>0){
                Integer node = stack1.pop();
                stack2.push(node);
            }
        }
        return stack2.pop();
    }

    /**
     * 第六题：旋转数组的最小数字
     * 最起码暴力法求解 循环遍历一遍
     * 或者下面的方法
     */
    public int minNumberInRotateArray(int [] array) {
        int length = array.length;
        if(length==0){
            return 0;
        }
        int left = 0;
        int right = length-1;
        int min = array[0];
        while(left<right){
            if(array[left]>array[left+1] || array[right]<array[right-1]){
                min = Math.min(min,array[left+1]);
                min = Math.min(min,array[right]);
                break;
            }
            left++;
            right--;
        }
        return min;
    }
    /** 旋转数组的最小数字   ——  也可以通过二分查找的变相来解决（有序：二分查找一般通过和目标值target进行比较，没有目标值就通过与断点进行比较）
     *      本题有是三种情况    通过与非递减序列的右端点进行比较
     */
    public int minNumberInRotateArray2(int [] array) {
        int start = 0;
        int last = array.length - 1;
        int mid = 0;
        while(start<last){
            if(array[start] < array[last]){
                return array[start];
            }
            mid = (start+last) / 2;
            if(array[mid] < array[last]){
                last = mid;
            }else if(array[mid] > array[last]){
                start = mid+1;
            }else{
                last--;
            }
        }
        return array[start];
    }

    /** 第七题：斐波那契数列  常用思路：寻找 f(n) 与 f(n-1)之间的规律，递归
     *              动态规划       循环数组，通过往前移动两个变量来减少递归算法的大量重复计算
     */
    public int Fibonacci(int n) {
        if(n==0 || n==1){
            return n;
        }
        return Fibonacci(n-1) + Fibonacci(n-2);
    }

    public int Fibonacci2(int n) {
        if(n==0 || n==1){
            return n;
        }
        int a=0,b=1,c=0;
        for (int i = 2; i <= n; i++) {
             c = a + b;
             a = b;
             b = c;
        }
        return c;
    }

    /** 第八题：青蛙跳台阶问题 —— 和上一题思路一样的，不同的点就是在 第一两项的起始值不一样而已！！
     *
     */
    public int JumpFloor(int target) {
        if(target==1 || target==2){
            return target;
        }
        int first=1,second=2,result=0;
        for (int i = 3; i <= target; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * 第九题：变态跳台阶：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     *
     *  It can be found: f (n) = 2f(n-1)
     *
     */
    public int jumpFloorII(int target) {
        if(target==0 || target==1){
            return 1;
        }
        return 2*jumpFloorII(target-1);
    }

    /**
     * 第十一题：二进制中1的个数 —— 通过与自身-1做与运算，每次运算都会除去最右边的1
     */
    public int NumberOf1(int n) {
        int count=0;
        while(n!=0){
            count++;
            n = n & (n-1);
        }
        return count;
    }
    // 使用api
    public int numberOf1(int n) {
        String str = Integer.toBinaryString(n);
        String[] split = str.split("");
        int result=0;
        for (int i = 0; i < split.length; i++) {
            if(split[i].equals("1")){
                result++;
            }
        }
        return result;
    }

    /**
     * 第十二题：数值的整数次方 —— 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。 保证base和exponent不同时为0
     *                  注意：整数有可能为负数的情况，所以的考虑到； 可以使用api，求绝对值函数
     */
    public double Power(double base, int exponent) {
        double result=1;
        int ex = Math.abs(exponent);
        while(ex>0){
            result *= base;
            ex--;
        }
        if(exponent<0){
            result = 1/result;
        }
        return result;

        // 直接使用api库函数
//        return Math.pow(base,exponent);
    }

    /**
     * 第十三题：调整数组顺序使奇数位于偶数前面
     *
     * 两种思路：暴力求解循环遍历一遍，并且申请两个临时的大小一样的数组，用来分别存奇偶数，然后再保持原数组顺序合并一个数组并返回（空间复杂度比较大）
     * 方法二：由于具有保持原来数组奇偶数相对顺序一致，可以使用插入排序的思想来做；如果不需要保持原来的相对顺序时：可以使用双指针来做
     */
    public int[] reOrderArray (int[] array) {
        if(array==null || array.length==0){
            return array;
        }
        int j;
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 0){
                continue;
            }
            int temp = array[i];
            j = i-1;
            while(j>=0 && (array[j]%2==0)){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
        return array;
    }

    /**
     *  第十四题：链表中倒数第K个节点
     */
    // 思路一：画图发现：倒数第k个 就是 正数第n+1-k个  （即标号间相加为：n+1） 从而会通过统计链表的长度继而来正向循环获取到正数师第 n+1-k 个节点
    public ListNode FindKthToTail (ListNode pHead, int k) {
        int length = getLength(pHead);
        if(length<k){
            return null;
        }
        for (int i = 1; (i < (length+1-k))&&(pHead!=null); i++) {
            pHead = pHead.next;
        }
        return pHead;
    }
    private int getLength(ListNode pHead) {
        if(pHead == null){
            return 0;
        }
        int length=0;
        while(pHead!=null){
            length++;
            pHead = pHead.next;
        }
        return length;
    }

    // 思路二：快慢指针法  核心要点：构建前后两个指针间相距 K 步 ——> 之后两个指针一同往前走直到前面的快指针走过剩下的所有的节点等于null时，此时
    //    两个指针间相距k步，也即：后面的慢指针距离尾节点 k-1 步，指向的便是倒数第k个节点  （比如倒数第2个节点，距离尾节点为2-1）
    public ListNode FindKthToTail2 (ListNode pHead, int k) {
        ListNode fast = pHead,slow = pHead;
        for (int i = 0; i < k; i++) {
            if(fast==null) return null;
             fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 第十五题：反转链表：输入一个链表，反转链表后，输出新链表的表头。
     */
    public ListNode ReverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode pre = null,cur=head,after=null,result=null;
        while(cur!=null){
            after = cur.next;
            if(after==null){
                result = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = after;
        }
        return result;
    }

    /**
     * 第十六题：合并两个排序的链表
     */
    // 递归
    public ListNode Merge(ListNode pHead1,ListNode pHead2){
        if(pHead1==null){
            return pHead2;
        }
        if(pHead2==null){
            return pHead1;
        }
        if(pHead1.val > pHead2.val){
            pHead2.next = Merge(pHead1,pHead2.next);
            return pHead2;
        }else {
            pHead1.next = Merge(pHead1.next,pHead2);
            return pHead1;
        }
    }
    // 直接按值大小合并
    public ListNode Merge2(ListNode pHead1,ListNode pHead2){
        if(pHead1==null){
            return pHead2;
        }
        if(pHead2==null){
            return pHead1;
        }
        ListNode result=new ListNode(-1);
        ListNode head=result;
        while(pHead1!=null && pHead2!=null){
            if(pHead1.val>pHead2.val){
                head.next=pHead2;
                pHead2 = pHead2.next;
            }else {
                head.next=pHead1;
                pHead1 = pHead1.next;
            }
            head = head.next;
        }
        if(pHead1==null){
            head.next = pHead2;
        }
        if(pHead2==null){
            head.next = pHead1;
        }
        return result.next;
    }

    /**
     * 第十七题：树的子结构
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root1==null || root2==null){
            return false;
        }
        return findSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }
    private boolean findSubtree(TreeNode root1,TreeNode root2){
        // root2 为空说明遍历完了以上都符合
        if(root2==null){
            return true;
        }
        // root2不为空并且root1为空则说明不匹配
        if(root1==null){
            return false;
        }
        return root1.val==root2.val && findSubtree(root1.left,root2.left) && findSubtree(root1.right,root2.right);
    }

    /**
     * 第十八题：二叉树的镜像 —— 二叉树的操作最经典思想递归
     */
    // 递归思路 —— 深度优先遍历
    public TreeNode Mirror (TreeNode pRoot) {
        if(pRoot==null){    // 递归函数出口
            return null;
        }
        TreeNode temp = pRoot.left;
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = Mirror(temp);
        return pRoot;
    }
    // 从符合本题题目要求出发：镜像就是逐层逐个对二叉树的每一个节点进行左右子子树的交换嘛 —— 可以采用广度优先遍历（广度优先遍历：一般借助栈实现）
    public TreeNode Mirror2 (TreeNode pRoot) {
        if(pRoot==null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(pRoot);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            // 交换node的左右子子树，但是交换前需要将该节点的左右子子树入栈以便下次循环（这个是常规套路）
            if(node.left!=null){
                stack.add(node.left);
            }
            if(node.right!=null){
                stack.add(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return pRoot;
    }

    /**
     * 第十九题：顺时针打印矩阵：并且从外向内里面打印
     * 本题最好的办法就是定义四个标量，然后按照题目要求方向的顺序循环遍历，并且更新标量的值，并判断是否越界
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<>();
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(true){
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            up++;
            if(up>down){        // 判断是否越界，越界则结束循环
                break;
            }
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if(left>right){
                break;
            }
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            down--;
            if(up>down){
                break;
            }
            for (int i = down; i >= up; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            if(left>right){
                break;
            }
        }
        return result;
    }

    /**
     * 第二十题：包含min函数的栈：定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     *
     * 没什么高深的地方，题，题目的要求就是按照你现有的知识去设计一个特殊的栈，特殊的栈包含有min函数，并且该函数的时间复杂度为O(1)
     * 所以借助栈实现，本题的特殊栈 —— 那说白了就是需要维护好最小值栈的数据一致性
     */
    static class MinStack{
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public void push(int node){
            if(minStack.isEmpty() || node<minStack.peek()){
                minStack.push(node);
            }
            stack.push(node);
        }

        public int pop(){
            Integer result = stack.pop();
            if(result<=minStack.peek()){
                minStack.pop();
            }
            return result;
        }

        public int top(){
            return stack.peek();
        }

        public int min(){
            return minStack.peek();
        }
    }

    /**
     * 第二十一题：栈的压入弹出序列：申请一个栈 + 一个变量j来保持遍历弹出序列的位置
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length==0 || popA.length==0 || pushA.length!=popA.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j=0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while(!stack.isEmpty() && stack.peek()==popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 第二十二题：从上往下打印二叉树 —— 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
            result.add(node.val);
        }
        return result;
    }

    /**
     * 第二十三题：二叉搜索树的后序遍历序列
     * 题目描述：    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。
     * 假设输入的数组的任意两个数字都互不相同。（ps：我们约定空树不是二叉搜素树）
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence==null || sequence.length==0){
            return false;
        }
        int length = sequence.length;
        return verifyHelp(sequence,0,length-1);    // 强化这种思想构造一个辅助函数
    }
    // 判断给定序列在指定范围位置是否是二叉搜索树
    private boolean verifyHelp(int[] sequence, int start, int end) {
        if(start>=end){     // recursiveExit:Start=end时为只有一个节点
            return true;
        }
        int root = sequence[end];
        int i=start;
        for (; i < sequence.length; i++) {
            if(root<sequence[i]){
                break;
            }
        }
        int j=i;
        for (int k = j+1; k < sequence.length; k++) {   //判断临界点右边是否有比根节点更小的值，存在的话则不满足二叉搜索树的条件
            if(sequence[k]<root){
                return false;
            }
        }
        return verifyHelp(sequence,start,j-1) && verifyHelp(sequence,j,end-1);
    }


}

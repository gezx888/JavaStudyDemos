package dataStructure.jzOffer;

import java.util.ArrayList;
import java.util.Stack;

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

        return null;
    }





















}

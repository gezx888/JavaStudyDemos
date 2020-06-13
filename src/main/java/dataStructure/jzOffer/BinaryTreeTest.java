package dataStructure.jzOffer;

import java.util.Stack;

/**
 * @description: 该类写的是剑指offer与二叉树相关的一些算法面试题
 * @author: gezx
 * @date: 2020/6/13 15:32
 */
public class BinaryTreeTest {
    /**
     * 二叉树的内部节点类
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    /**
     * case27：二叉树的镜像：完成一个函数，输入二叉树，该函数输出二叉树的镜像
     * 重要思想：二叉树部分最最重要的思想：递归解法！！
     * 方法一：使用递归求解，依次对当前节点的左右子树节点递归求解镜像
     * 特殊情况（也即是递归出口）：当前节点为空null，返回null。
     *
     * 方法二：利用外部存储空间（比如栈）
     * 步骤流程：1、初始化一个栈，将树的根节点加入到栈中；
     * 2、然后只要栈不为空就一直循环栈，
     * 每一次循环过程中：出栈 --> 交换该节点的左右子树，（ 但是在交换左右子树之前，需要先判断出栈节点元素的左右子树是否为空，
     * 不为空时需要将其入栈，以便于下次弹栈，并交换他们的左右子树！）
     *
     * @return
     */
    // 方法一：递归解法
    private TreeNode mirrorTree(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode tem = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tem);
        return root;
    }
    // 方法二：利用外部空间 栈
    private TreeNode mirrorTree2(TreeNode root){
        if(root==null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left!=null){
                stack.add(node.left);
            }
            if(node.right!=null){
                stack.add(node.right);
            }
            TreeNode tem = node.left;
            node.left = node.right;
            node.right = tem;
        }
        return root;
    }

    /**
     * case28：写一个算法判断二叉树是否为对称的二叉树
     * 思路：对称二叉树的定义：对于树中任意两个对称的节点 L 和 R 一定有：
     * （1）L.val=R.val
     * （2）L.left.val=R.right.val
     * （3）L.right.val=R.left.val
     * 从而可以根据以上规律，从顶至底递归，判断每对节点是否对称，从而判断二叉树是否为对称二叉树
     *
     * 算法流程：
     * 1、特例处理：若根节点root为空，则直接返回true。
     * 2、返回值：即 recur(root.left,root.right);
     *
     * @param root
     * @return
     */
    private boolean isSymmetric(TreeNode root){
        if(root==null) return true;
        return recur(root.left,root.right);
    }
    private boolean recur(TreeNode left, TreeNode right) {
        if(left==null && right==null) return true;
        if(left==null || right==null || left.val!=right.val) return false;
        return recur(left.left,right.right) && recur(left.right,right.left);
    }

}

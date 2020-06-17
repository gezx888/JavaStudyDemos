package dataStructure.jzOffer;

import java.util.LinkedList;
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

    /**
     * case55-1：二叉树的深度
     * 二种方法：一：递归求法：返回左右子树中最大的深度+1即可
     *
     * 二：通过层序遍历（需要申请外部临时存储空间）二叉树的层序遍历一般都是借助队列来辅助实现
     * 循环遍历当层中的所有节点，每次循环中 每一个节点的左右子树不为空的时候需要将它分别加入容器中，然后赋值
     * 给队列，每一层遍历完之后，树的深度结果res+1，一直层序循环直到队列为空退出！
     *
     * @param root
     * @return
     */
    // 方法一：递归
    private int maxDepth(TreeNode root){
        if(root==null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    // 通过借助队列 层序遍历二叉树求解
    private int maxdepth2(TreeNode root){
        if(root==null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>(),tem;
        int res = 0;
        queue.add(root);
        while (!queue.isEmpty()){
           tem = new LinkedList<>();
           for(TreeNode node : queue){
               if(node.left!=null) tem.add(node.left);
               if(node.right!=null) tem.add(node.right);
           }
           queue = tem;
           res++;
        }
        return res;
    }

    /**
     * case55-2：判断是否为平衡二叉树
     * 思路：方法一（从顶之底）：根据平衡二叉树的定义，当判断得到所有节点都满足条件定义后说明此树为平衡二叉树（借助求二叉树深度函数）
     *
     * 方法二：后序遍历+剪枝（从底至顶）：思路是对二叉树做后序遍历，从底至顶返回子树深度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
     * 也是需要构造一个外部函数：返回值 当前子树深度（当其左右节点深度差<=1时，返回其深度；当其左右节点深度差>2时，返回-1,代表此树不是平衡二叉树）
     *
     * @return
     */
    // 方法一：方法一（从顶之底）递归求解
    private boolean isBalanced(TreeNode root){
        if(root==null) return true;
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right))<=1;
    }

    // 方法二：后序遍历+剪枝（从底至顶）
    private boolean isBalanced2(TreeNode root){
        return recueMethod(root) != -1;
    }

    // 方法二的辅助函数(下面的-1是自己定义的，你也可以定义成其他数)
    private int recueMethod(TreeNode root) {
        if(root==null) return 0;
        int left = recueMethod(root.left);
        if(left==-1) return -1;
        int right = recueMethod(root.right);
        if(right==-1) return -1;
        return Math.abs(left-right) < 2?Math.max(left,right)+1:-1;
    }

}

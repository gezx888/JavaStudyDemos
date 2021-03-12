package dataStructure.jzOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
     剑指offer case17：树的子结构：输入两颗树A B，判断B是否为A的子结构（约定空树不是任何树的子结构）
                算法思路：两个方法：一个方法负责B是否是A的子结构，另外一个方法负责匹配A是否是以B节点开始的子结构
                算法步骤：（1）第一个方法看A树当前节点是否是以节点B开始的子结构，否则向B的左右孩子去寻找
                        （2）第二个方法看传入的A树的当前节点是否和B相等，且左右孩子也完美匹配
     * @description:
     * @author: gezx
     * @date: 2021/3/9
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2){
        if(root1 == null || root2 == null){
            return false;
        }
        return findSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }

    private boolean findSubtree(TreeNode root1, TreeNode root2) {
        // root2 为空说明以上都符合
        if(root2 == null){
            return true;
        }
        // root2不为空并且root1为空则说明不匹配
        if(root1 == null){
            return false;
        }
        return root1.val == root2.val && findSubtree(root1.left,root2.left) && findSubtree(root1.right,root2.right);
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
    // 方法一：递归解法（DFS —— 深度优先搜索）
    private TreeNode mirrorTree(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode tem = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tem);
        return root;
    }
    // 方法二：利用外部空间 栈 （BFS —— 广度优先搜索）
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

    /**
        剑指offer case22 从上往下打印二叉树：其实就是二叉树的=广度优先遍历，同一层的节点按照从左到右的顺序输出
                            这样的遍历一般需要借助外部数据结构实现，比较常见的就是队列 Queue来实现先进先出的目的
     * @description:
     * @author: gezx
     * @date: 2021/3/9
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        // 队列queue用来保存当前遍历到了哪个节点，注意某一个节点的左右子节点需要一次性进行入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 只要队列不为空，说明还有节点，说明还没有遍历完，继续
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            result.add(node.val);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return result;
    }

    /**
         剑指offer case23：二叉搜索树（二叉排序树 / 二叉查找树）的后序遍历序列：输入一个序列数组，判断该数组是否是二叉搜索树的后序遍历序列
     * @description:            解题思路：依据二叉查找树的后序遍历数组，可以知道：二叉树查找树的根节点为数组的最后一个数
     *                      条件（1）从头开始遍历序列数组，找到第一个比最后一个数大的数，则以该节点为分界点，左边为左子树，此时已经能保证其都比根节点小
     *                      条件（2）分界点右边的序列为 右子树，需要循环遍历保证右边的每一个数都比根节点大（否则不满足条件返回false退出）
     *                      之后采用递归算法判断分界点 左右子树情况
     * @return:
     * @author: gezx
     * @date: 2021/3/9
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence==null ||sequence.length == 0){
            return false;
        }
        return verify(sequence,0,sequence.length-1);
    }

    private boolean verify(int[] sequence, int start, int end) {
        if(start >= end){
            return true;
        }
        int root = sequence[end];   // 拿到当前序列的根节点
        int i = start;
        for (; i < sequence.length; i++) {
            if(sequence[i] > root){
                break;
            }
        }
        int j = i;
        for (; j < sequence.length; j++) {
            if(sequence[j] < root){
                return false;
            }
        }
        return verify(sequence,0,i-1) && verify(sequence,i,end-1);
    }



}

package dataStructure.jzOffer;

import org.junit.Test;

/**
 * 该类写的是剑指offer与数组相关的一些面试题
 * @author gezx
 * @date 2020/6/12 10:24
 */
public class ArrayTest {
    @Test
    public void test(){
        int[][] array = {{1,   4,  7, 11, 15},
                         {2,   5,  8, 12, 19},
                         {3,   6,  9, 16, 22},
                         {10, 13, 14, 17, 24},
                         {18, 21, 23, 26, 30}};
        boolean flag = findNumberIn2DArray(array, 36);
        System.out.println(flag);
        System.out.println(fib(8));
    }

    /**
     * case4：二维数组中的查找（二位数组线性有序）：从而可以利用这个特点进行查找，使其时间复杂度为O(m+n)
     * 方法二：暴力求解，时间复杂度为O(mn) 两层循环，依次与目标值进行比较
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix,int target){
        if(matrix==null || matrix.length==0 || matrix[0].length==0){
            return false;
        }
        int rows = matrix.length,columns = matrix[0].length;
        int row = 0,column = columns -1;
        while(row < rows && column >= 0){
            int num = matrix[row][column];
            if(num == target){
                return true;
            }else if(num < target){
                row++;
            }else{
                column--;
            }
        }
        return false;
    }

    /**
     * case10-1：斐波那契数列（一般使用递归算法，但递归算法会产生很多重复计算，效率低）
     * 本方法使用的是非递归算法，使用的是动态规划思想。见有道云笔记
     * @param n
     * @return
     */
    private int fib(int n){
        if(n==0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }

    /**
     * case10-2：青蛙跳台阶问题（此类求多少种可能性的题目一般都有递推性质，即f(n) 和 f(n-1)…f(1) 之间是有联系的。）
     * 假设跳上 n 级台阶有 f(n) 种跳法。所有跳法中，青蛙的最后一步都只是只有两种情况：跳上1级台阶 或 跳上2级台阶。
     * 1、当为 1 级台阶： 剩 n−1 个台阶，此情况共有 f(n−1) 种跳法；
     * 2、当为 2 级台阶： 剩 n−2 个台阶，此情况共有 f(n−2) 种跳法。
     *
     * f(n) 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2)，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 n 项的值
             但是注意和 斐波那契 数列问题不同的是：起始数字不同
     青蛙跳台阶问题： f(0)=1, f(1)=1, f(2)=2；
     斐波那契数列问题： f(0)=0, f(1)=1, f(2)=1。
     *
     * @param n
     * @return
     */
    private int numWays(int n){
        if(n==0 || n==1){
            return 1;
        }
        int[] dp = new int[n+1];
        // 初始化两个起始值，后面交替前行
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }



}

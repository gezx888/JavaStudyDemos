package dataStructure.jzOffer;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
     * case3: 找出数组中任意一个重复的数字：由于是找任意一个重复数字，所以可以使用Set集合的
     * 不可重复性+遍历数组，直接返回第一个未能添加成功的数字即可
     */
    public int findRepeatNumber(int[] nums){
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for(int num : nums){
            if(!set.add(num)){
                repeat = num;
                break;
            }
        }
        return repeat;
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

    /**
     * case11：旋转数组的最小值求解：一个递增数组将尾部若干个数移到数组最前面，称为数组的一次旋转
     * 题目：输入一个递增数组的一个旋转数组，求旋转数组的最小值
     *
     * 思路：方法一：暴力求解：对旋转数组依次遍历一遍求出最小值（一般达不到面试官想要答案）
     * 方法二：双指针法 —— 左指针left：指向数组开头   右指针right：指向数组结尾元素
     * 两边同时反向移动指针，当出现nums[left]>nums[left+1] 或者 nums[right] < nums[right-1]的时候
     * nums[left+1] 或者 nums[right]便是数组中的最小值
     *
     * @param nums
     * @return
     */
    private int minArray(int[] nums){
        int length = nums.length;
        if(length==0){
            return -1;
        }
        int left=0,right=length-1;
        int min = nums[0];
        while (left<right){
            if(nums[left]>nums[left+1] || nums[right]<nums[right-1]){
                min = Math.min(min, nums[left + 1]);
                min = Math.min(min, nums[right]);
                break;
            }
            left++;
            right--;
        }
        return min;
    }

    /**
     * 事先需要了解一个知识点：
     * 若 n & 1 == 0，则 n 的最右一位为0，n 为偶数
     * 若 n & 1 == 1，则 n 的最右一位为1，n 为奇数
     */

    /**
     * case21：调整数组的顺序：是奇数位于偶数的前面
     * 讲两个思路：
     * 方法一：简单粗暴法：定义一个临时数组，用来按照题目要求存好元素，然后直接返回
     *
     * 方法二：双指针法：分别指向数组前后两端，左指针向右移直到遇到偶数停止下来，等待
     * 右指针向左移动直到遇到奇数，这个时候将两个指针指向的两个数互换位置，最后直到左右指针
     * 相等时 left == right 是退出循环，返回数组即可！
     *
     * 其实还有方法三：使用快慢指针法：
     * 一开始快慢指针都指向数组最低位元素，然后使用慢指针slow存放下一个奇数即将要存放的位置，快指针fast依次遍历数组元素，
     * 快指针一直向右移动直到遇到奇数时，这时候当快指针不等于慢指针时（fast != slow）将快指针元素 与 慢指针指向的元素互相交换
     *
     * @return
     */
    // 方法一：
    private int[] exchange(int[] nums){
        if(nums==null || nums.length==0){
            return nums;
        }
        int left=0,right=nums.length-1;
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if((nums[i]&1) == 0){ // 说明为偶数
                temp[right--] = nums[i];
            }else {
                temp[left++] = nums[i];
            }
        }
        return temp;
    }
    // 方法二;
    private int[] exchange2(int[] nums){
        if(nums==null || nums.length==0){
            return nums;
        }
        int left=0,right=nums.length-1;
        while (left<right){
            // 一直向右移动左指针直到遇到偶数停下来
            while((left<right) && (nums[left] & 1) == 1 ){
                left++;
            }
            // 一直向左移动右指针直到遇到奇数停下来
            while((left<right) && (nums[right] & 1) == 0){
                right--;
            }
            // 交换上面两个数的位置
            if(left < right){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        return nums;
    }



}

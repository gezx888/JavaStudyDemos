package dataStructure.sortexercise;

/**
 * @className: SortTest
 * @description:        该类写我们最常见的排序算法：包括冒泡排序、选择排序、插入排序、希尔排序、堆排序、快速排序 以及 归并排序等
 * @author: gezx
 * @date: 2021/3/26
 **/
public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[]{3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};

        int[] ints = insertSort(array);

        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "  ");
        }
    }

    /**
     * @description:  冒泡排序：一种简单的稳定的排序算法，即通过重复走访比较相邻的两个元素，如果前面的元素比后面的大就交换他们的位置，这样每进行一趟排序之后
     *                          便会使本趟中最大的数排到正确的位置（从而假如有n个数，则需要进行 n-1 趟就可以完成整个排序过程，使数列有序！！）
     *
     *                注意：可进行优化，即不一定每一趟都得进行，数列有可能提前就已经全部有序了  既可以设计一个标记位，如果本趟没有进行交换，则可以提前退出比较了
     * @return:
     * @author: gezx
     * @date: 2021/3/26
     */
    // 常规写法：
    public static int[] bubbleSort(int[] array){
        for (int i = 0; i < array.length-1; i++) {      // 排序趟数
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    // 简单优化：
    public static int[] bubbleSort2(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
        return array;
    }

    /**
     * @description:    选择排序：算法原理：首先在未排序的序列中找到最小（大）的元素放到排序序列的起始位置或是最后的位置，然后，再从剩余未排序的序列中找到最小（大）的
     *                              元素放到正确的位置；依次内推，直到所有的元素都排序完毕。
     * @return:
     * @author: gezx
     * @date: 2021/3/26
     */
    public static int[] selectSort(int[] array){
        int minIndex,temp;
        for (int i = 0; i < array.length-1; i++) {     // 比较趟数
            minIndex = i;
            for (int j = i+1; j < array.length; j++) {      // 每一趟中排出最大的数，排在正确的位置
                if(array[minIndex] > array[j]){
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    /**                 通过逐步构建有序序列达到整个序列有序的目的
     *
     * @description:    插入排序：算法原理：通过逐步构建有序序列，然后在未排序的序列中，每次选取一个数，在已排序序列中从后往前进行扫描，找到该数正确的位置进行插入
     * @return:
     * @author: gezx
     * @date: 2021/3/26
     */
    public static int[] insertSort(int[] array){
        int temp,j;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            j = i-1;
            while(j>=0 && temp < array[j]){      // 确定待插入位置 找到正确的插入位置就需要调出循环，但是循环多少次能找到插入位置不确定 (次数不确定的一般使用while循环)
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = temp;
        }
        return array;
    }

    /**
     * @description:    快速排序：算法原理：利用分治法对待排序的序列进行分治排序，主要思想是：通过一趟排序，将序列排成两部分，其中一部分的数都比关键字小，
     *                  另一部分的数都比关键字大；然后对这两部分分别使用这种方式进行排序，通过递归运算达到整个序列有序的目的。
     *                  （1）首先需要选择一个基准数，可以选取第一个
     *                  （2）遍历数组，将小于基准数的放置于基准数的左边，大于基准数的放在基准数的右边； 即是需要将基准数挪到某一个正确的位置 K
     *                  （3）怎样才能找到位置 K 呢？  使用双指针法：从数组两端分别进行比对
     *                  （4）先从最右位置往左开始找直到找到一个小于基准数的值，记录下该值的位置（记作 j）
     *                  （5）再从最左位置往右找直到找到一个大于基准数的值，记录下该值的位置（记作 i）。
     *                  （6）如果位置i<j，则交换i和j两个位置上的值，然后继续从(j-1)的位置往前和(i+1)的位置往后重复上面比对基准数然后交换的步骤。
     *                  （7）如果执行到i==j，表示本次比对已经结束，将最后i的位置的值与基准数做交换，此时基准数就找到了临界点的位置k，位置k两边的数组
     *                          都比当前位置k上的基准值或都更小或都更大。
     *                  （8）通过相同的排序思想，分别对 两边的数组进行快速排序，左边对[left, k-1]子数组排序，右边则是[k+1, right]子数组排序。
     * @return:
     * @author: gezx
     * @date: 2021/3/26
     */
    public static int[] quickSort(int[] array){
        if(array == null || array.length<=1){
            return array;
        }
        return sortHelp(array,0,array.length-1);
    }

    private static int[] sortHelp(int[] array, int left, int right) {
        if(left > right){
            return array;
        }
        int base = array[left];
        while(left != right){
           while(array[right]>base && left<right){
               right--;
           }
           while(array[left]<base && left<right){
               left++;
           }
           if(left < right){
               int temp = array[left];

           }
        }

        return new int[0];
    }

}

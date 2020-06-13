package dataStructure.jzOffer;

import org.junit.Test;

/**
 * 该类写的是剑指offer与字符串相关的一些面试题
 * @author gezx
 * @date 2020/6/12 10:48
 */
public class StringTest {

    @Test
    public void test(){
        String string = replaceSpace("We  are h a  ppy.");
        System.out.println(string);
    }

    /**
     * case5：替换空格：将给定字符串中的空格全部替换掉
     * @param str
     * @return
     */
    private String replaceSpace(String str){
        int length = str.length();
        char[] charArray = new char[length*3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if(c == ' '){
                charArray[size++] = '%';
                charArray[size++] = '2';
                charArray[size++] = '0';
            }else{
                charArray[size++] = c;
            }
        }
        return new String(charArray,0,size);
     //   return str.replaceAll(" ","%20" );  方法二：使用API全部替换
    }

    /**
     * case15：二进制中1的个数统计
     *
     * 思路：巧用 n & n-1 运算，因为每一次与n-1进行与操作的时候，都会将 n 的最右边的 1 消去变成 0；其余位没有改变！
     * 所以循环多少次就有多少个 1
     *
     * @param n
     * @return
     */
    private int hammingWeight(int n){
        int res = 0;
        while(n!=0){
            n &= n-1;  // 每一次与n-1进行与操作的时候，都会将 n 的最右边的 1 消去变成 0；其余位没有改变！
            res++;
        }
        return res;
    }




}

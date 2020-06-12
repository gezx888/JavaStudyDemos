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
     * 替换空格：将给定字符串中的空格全部替换掉
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

}

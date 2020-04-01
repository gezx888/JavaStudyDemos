package demos;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 本类主要演示StringUtils类中两个非空判断方法的区别
 * @author gezx
 * @date 2020/4/1 18:58
 */
public class NotNullTest {

    @Test
    public void test01(){
        // a 为一个有空格键构成的字符串，长度为5
        String a = "     ";
        // b 为空串
        String b = "";
        String c = null;

        System.out.println(a.length());  // 5
        System.out.println(b.length());  // 0

        System.out.println(StringUtils.isEmpty(a));   // false
        System.out.println(StringUtils.isEmpty(b));   // true
        System.out.println(StringUtils.isEmpty(c));   // true

        System.out.println(StringUtils.isBlank(a));   // true
        System.out.println(StringUtils.isBlank(b));   // true
        System.out.println(StringUtils.isBlank(c));   // true
    }

    @Test
    public void test02(){
        // a 为一个有空格键构成的字符串，长度为5
        String a = "     ";
        // b 为空串
        String b = "";
        String c = null;

        System.out.println(a.length());  // 5
        System.out.println(b.length());  // 0

        System.out.println(StringUtils.isNotEmpty(a));   // true
        System.out.println(StringUtils.isNotEmpty(b));   // false
        System.out.println(StringUtils.isNotEmpty(c));   // false

        System.out.println(StringUtils.isNotBlank(a));   // false
        System.out.println(StringUtils.isNotBlank(b));   // false
        System.out.println(StringUtils.isNotBlank(c));   // false
    }

}

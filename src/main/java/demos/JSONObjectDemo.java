package demos;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Describe: 主要演示熟悉JsonObject的相关使用
 * @Author: Gezx
 * @Date: 2020/4/1 22:08
 */
public class JSONObjectDemo {

    /**
     * 主要测试json格式字符串转JSONObject对象:通过使用对象 JSONObject 的 fromObject 方法
     */
    @Test
    public void test01(){
        System.out.println("---json格式字符串转JSONObject对象---");
        String jsonStr = "{\"userName\":\"geZhiXiang\",\"passWord\":\"123456\"}";
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String userName = jsonObject.getString("userName");
        String passWord = jsonObject.getString("passWord");
        System.out.println(userName + ":" + passWord);
    }

    /**
     * 将JSONObject对象封装成json字符串 直接使用 toString 方法
     */
    @Test
    public void test02(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","gzx");
        jsonObject.put("age",24);
        jsonObject.put("interests",new ArrayList<>(Arrays.asList("篮球","足球","羽毛球")));
        System.out.println("jsonObject对象--->json \n " + jsonObject.toString());
    }

    @Test
    public void test03(){
        System.out.println("JavaBean对象可以先转JSONObject对象，然后通过toString()方法转成json字符串");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("gzx");
        userInfo.setPassword("123456");
        JSONObject jsonObject = JSONObject.fromObject(userInfo);
        System.out.println("JavaBean-->json \n" + jsonObject.toString());
    }









}

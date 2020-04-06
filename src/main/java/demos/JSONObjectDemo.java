package demos;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

        System.out.println("通过com.alibaba.fastjson.JSONObject将str转化为相应的JSONObject对象");
        com.alibaba.fastjson.JSONObject jsonObj = com.alibaba.fastjson.JSONObject.parseObject(jsonStr);
        System.out.println(jsonObj.getString("userName"));
        System.out.println(jsonObj.getString("passWord"));

        System.out.println("==================");
        System.out.println("通过net.sf.json.JSONObject将str转化为相应的JSONObject对象");
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String userName = jsonObject.getString("userName");
        String passWord = jsonObject.getString("passWord");
        System.out.println(userName + ":" + passWord);
    }

    /**
     * 将JSONObject对象封装成json字符串 直接使用 toString 方法
     * 即：通过原生生成json数据格式。
     */
    @Test
    public void test02(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","gzx");
        jsonObject.put("age",24);
        jsonObject.put("interests",new ArrayList<>(Arrays.asList("篮球","足球","羽毛球")));
        System.out.println("jsonObject对象--->json \n " + jsonObject.toString());
    }

    /**
     * JavaBean对象可以先转JSONObject对象，然后通过toString()方法转成json字符串
     */
    @Test
    public void test03(){
        System.out.println("JavaBean对象可以先转JSONObject对象，然后通过toString()方法转成json字符串");
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("gzx");
        userInfo.setPassword("123456");
        JSONObject jsonObject = JSONObject.fromObject(userInfo);
        System.out.println("JavaBean-->json \n" + jsonObject.toString());
    }

    /**
     * 主要测试一下两个不同包下面的JSONObject对象的一点区别
     */
    @Test
    public void test04(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("gzx");
        userInfo.setPassword("123456");

        String jsonStr = com.alibaba.fastjson.JSONObject.toJSONString(userInfo);
        System.out.println("由com.alibaba.fastjson.JSONObject包下方法转换而来" + jsonStr);

        JSONObject jsonObject = JSONObject.fromObject(userInfo);
        System.out.println("由net.sf.json.JSONObject包下方法转换而来 " + jsonObject.toString());
    }

    /**
     * 通过hashMap数据结构生成
     */
    @Test
    public void test05(){
        HashMap<String,Object> zhangsan = new HashMap();
        zhangsan.put("username","张三");
        zhangsan.put("password","123456++");
        zhangsan.put("gender","男");
        zhangsan.put("interests",new ArrayList<>(Arrays.asList("篮球","足球","羽毛球")));
        zhangsan.put("house", false);
        System.out.println(zhangsan.toString());
    }









}

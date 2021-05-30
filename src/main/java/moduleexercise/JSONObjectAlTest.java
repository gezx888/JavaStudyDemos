package moduleexercise;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * @author gezx
 * @date 2020/4/7 18:39
 */
public class JSONObjectAlTest {
    @Test
    public void test06(){
        // Json对象中是添加的键值对,JSONArray中添加的是Json对象
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        // JSONObject 对象中添加键值对
        jsonObject.put("name","gezx");
        jsonObject.put("password","123456");
        jsonObject.put("addr","bj-hd");
        jsonObject1.put("001","tom");

        // 将JSONObject对象添加到json数组中
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);

        List<JSONObject> jsonObjectList = doSomeThings(jsonArray);

        /**
         * 下面输出jsonArray的字符串形式，目的就是为了验证自己之前的一个错误
         * 将jsonArray传到 下面的doSomeThings()方法，返回了上面的jsonObjectList,但输出jsonArray的字符串形式，也仍然变了
         * 所以它传到就是一个引用，注意这点，所以不要以为它没变，对它已经进行处理的了！
         */
        System.out.println(jsonArray.toString());
    }

    private List<JSONObject> doSomeThings(JSONArray jsonArray){
        List<JSONObject> jsonObjectList = jsonArray.toJavaList(JSONObject.class);
        for(JSONObject obj : jsonObjectList){
            int size = obj.size();
            if(size < 2){
                obj.put("myKey","增加一个");
            }
        }
        return jsonObjectList;
    }
}

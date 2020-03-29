package cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe: 服务端获取Cookie并解析Cookie中的信息数据
 * @Author: Gezx
 * @Date: 2020/3/29 17:56
 */
public class OtherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        /**
         * 获取请求中的Cookie 从而可以对拿到的Cookie进行相关的操作
         * 拿到Cookie中的信息，从而实现会话不同请求间的数据传递
         */
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            System.out.println(cookie.getName() + "===" + cookie.getValue());
            if(cookie.getName().equals("userName") && cookie.getValue().equals("gezx")){
                // ...
            }else{
                // ...
            }
        }
    }
}

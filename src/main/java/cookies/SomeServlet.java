package cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Describe: Cookie学习类
 * @Author: Gezx
 * @Date: 2020/3/29 16:57
 */
//@WebServlet(name = "SomeServlet")
public class SomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成Cookie对象
        Cookie cookie = new Cookie("company","glodon");
        Cookie cookie2 = new Cookie("employee","gezx");

        // 指定Cookie的绑定路径。 注意，这里指定的路径要求必须要添加上项目的名称
        String contextPath = request.getContextPath();  // 这个为项目名称
//        cookie.setPath(contextPath + "/aaa/ccc");
//        cookie2.setPath(contextPath + "/ddd");

        /**
         * 设置Cookie的有效期，参数为整形数值，单位为秒
         * 大于0，表示将Cookie存放到客户端的硬盘
         * 小于0，与不设置效果相同，会将Cookie存放到浏览器的缓存
         * 等于0，表示Cookie一生成马上失效
         */
        cookie.setMaxAge(60 * 60 * 24);

        // 添加Cookie对象至response响应中
        response.addCookie(cookie);
        response.addCookie(cookie2);
        PrintWriter writer = response.getWriter();
        writer.println(contextPath);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

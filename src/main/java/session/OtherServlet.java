package session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Describe:
 * @Author: Gezx
 * @Date: 2020/3/29 20:26
 */
//@WebServlet(name = "seOtherServlet")
public class OtherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从请求域中拿取参数值
        String user = request.getParameter("user");
        /**
         * 一般从session对象中取指定参数的值时，用下面的带false参数的方法获取session对象
         * 即：有session就有老的session，没有的话也不新建了，因为即使新建的session
         * 肯定也没有指定参数的值
         */
        HttpSession session = request.getSession(false);
        String userName = null;
        if(session != null){
            userName = (String) session.getAttribute("userName");
        }

        PrintWriter writer = response.getWriter();
        writer.println("OtherServlet: user = " + user);
        writer.println("OtherServlet: userName = " + userName);
    }
}

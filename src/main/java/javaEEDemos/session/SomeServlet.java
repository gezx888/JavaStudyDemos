package javaEEDemos.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Describe:
 * @Author: Gezx
 * @Date: 2020/3/29 20:22
 */
public class SomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取用户提交参数
        String userName = request.getParameter("user");

        // 将参数放入request域中
        request.setAttribute("user","userName");
        /**
         * 获取session对象，此时用不带参数的方法，或true的，即有session即用老的session
         * 没有session的话则新建一个新的session
         */
        HttpSession session = request.getSession();
        session.setAttribute("userName",userName);

        response.getWriter().println("SomeServlet:" + userName);
    }

}

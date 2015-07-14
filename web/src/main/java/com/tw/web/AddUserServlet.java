package com.tw.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.io.PrintWriter;
import com.tw.core.entity.User;
import com.tw.core.UserDao;
import com.tw.core.userH;
/**
 * Created by twer on 7/11/15.
 */
@WebServlet("/web/AddUserServlet")
public class AddUserServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        CachedRowSet cachedRowSet = null;
//        PrintWriter out = response.getWriter();
//        String name = request.getParameter("username");
//        String sql="insert into user values('"+name+"')";
//        try {
//
//            new userH().InsertSql(sql);
//            response.sendRedirect("index.jsp");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CachedRowSet cachedRowSet = null;
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String sql="insert into user values('"+name+"')";
//        out.print("tyty");
        try {

//            String username = request.getParameter("username");
//            out.print(sql);
            User user = new User();
            user.setUsername(name);

            UserDao userdao=new UserDao();
            userdao.addUser(user);

//            new userH().InsertSql(sql);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


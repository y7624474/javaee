package com.tw.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.io.PrintWriter;

import com.tw.core.userH;
/**
 * Created by twer on 7/11/15.
 */
@WebServlet("/web/DelServlet")
public class DelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CachedRowSet cachedRowSet = null;
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String sql="delete from user where username=('"+name+"')";
        //out.print(sql);
        try {
            new userH().InsertSql(sql);
//            request.setAttribute("userList", cachedRowSet);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



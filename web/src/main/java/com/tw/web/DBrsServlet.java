package com.tw.web;

/**
 * Created by twer on 7/11/15.
 */
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;



import com.tw.core.userH;

import java.sql.ResultSet;


public class DBrsServlet extends HttpServlet  {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();

        String sql="select * from User";
        ResultSet rs=null;
        try {
            out.print((new userH().SelectSql(sql, rs)).toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
    }

}

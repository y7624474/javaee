package com.tw.core;

/**
 * Created by twer on 7/11/15.
 */

import java.sql.*;


public class DBConnect {


    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo?" + "user=root&password=yy");
        }

        // Do something with the Connection

        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
    public void CutConnection(Connection conn,ResultSet rs) throws SQLException {
        try {
            if (rs != null) ;
            if (conn != null) ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            conn.close();
        }
    }
}

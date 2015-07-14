package com.tw.core;

/**
 * Created by twer on 7/11/15.
 */
import java.sql.*;
import java.util.ArrayList;


public class userH {

    public String SelectSql(String sql, ResultSet rs){
        String usr="";
        try{
            Connection conn=DBConnect.getConnection();

            Statement statement=conn.createStatement();

            rs=statement.executeQuery(sql);

            while(rs.next()){

                usr+=(rs.getString("username"))+",";


            }
        }catch(Exception e){
            e.printStackTrace();
        }
    return usr;
    }

    public void InsertSql(String sql){
        try{

            Connection conn=DBConnect.getConnection();

            PreparedStatement ps=conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

package com.example.jdbc;

import java.sql.*;
import java.util.HashMap;

public class DB {
    Connection con;
    PreparedStatement preparedStatement;
    ResultSet rs;

    public DB(String dbUrl,String name,String passWord){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(dbUrl,name,passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String bvid){
        String sql="select * from IP_location where IP=?";
        String res="";
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,bvid);
            rs=preparedStatement.executeQuery();
            if(!rs.next()){
                return "null";
            }
            res = rs.getString("location");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public void add(String bvid,String url){
        String sql="insert into ip_location(IP,location) values(?,?)";
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,bvid);
            preparedStatement.setString(2,url);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public HashMap getAll() {
        HashMap stringStringHashMap = new HashMap<>();
        String sql="select * from IP_location";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                stringStringHashMap.put(resultSet.getString("IP"),resultSet.getString("location"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringStringHashMap;
    }

    public void put(String location){
        String sql="insert into location(location) values(?)";
        try {
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.setString(1,location);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.study.department;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Util {
    public Connection connection=null;
    public PreparedStatement pre=null;
    public ResultSet result=null;
    static String url=null;
    static String user=null;
    static String password=null;

    static{
        try {
            Properties pro=new Properties();
            FileReader reader=new FileReader("E:\\UltimateIDEA\\Project02\\Day03\\src\\resources\\jdbc.properties");
            pro.load(reader);
            reader.close();
            String driver=pro.getProperty("driver");
            url=pro.getProperty("url");
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            Class.forName(driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection connect(){
        try {
            connection= DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close(Connection connection,PreparedStatement pre,ResultSet result){
        if(result!=null){
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(this.pre!=null){
            try {
                pre.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.study.util;

import com.study.department.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUtil {
    private String admin;
    private String password;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean admin(String user,String password){
        boolean b=false;
        Util util=new Util();
        Connection connection=util.connect();
        String sql="select admin,password from user where admin=? and password=?";
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
            pre=connection.prepareStatement(sql);
            pre.setString(1,user);
            pre.setString(2,password);
            result=pre.executeQuery();
            if(result.next()){
                b=true;
            }else {
                b=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(connection,pre,result);
        }

        return b;
    }






}

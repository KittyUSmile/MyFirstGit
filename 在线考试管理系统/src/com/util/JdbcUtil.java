package com.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class JdbcUtil {
    private Connection conn = null;
    private PreparedStatement ps = null;
    public JdbcUtil() {
    }
    //静态代码块，加载即执行
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public Connection getCon() {
        //?serverTimezone=UTC：时区调整
        final String url = "jdbc:mysql://localhost:3306/systemForExam?serverTimezone=UTC";
        final String user = "root";
        final String password = "2002";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    //获取连接重载【通过监听器和servletContext快速拿到conn对象】
    public Connection getCon(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("connKey");
        Iterator iter = map.keySet().iterator();
        while(iter.hasNext()){
             conn = (Connection) iter.next();
            boolean flag = (boolean)map.get(conn);
            if(flag!=false) {
                map.put(conn,false);//表示有人使用
                break;
            }
        }
        return conn;
    }
    //拿到预处理对象
    public PreparedStatement CreateStatement(String sql) {
        try {
            ps = getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    //通过servletContext中的conn对象拿到预处理对象
    public PreparedStatement CreateStatement(String sql,HttpServletRequest request) {
        try {
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    //销毁 ResultSet 对象
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //销毁 PreparedStatement 对象
    public void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 销毁 Connection 对象
    public void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //统统销毁
    public void close(){
        close(ps);
        close(conn);
    }
    //除了conn，统统销毁,conn放回 map 继续使用
    public void close(HttpServletRequest request){
        close(ps);
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("connKey");
        map.put(conn,true);
    }
}

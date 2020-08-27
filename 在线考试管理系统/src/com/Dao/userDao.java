package com.Dao;

import com.entity.user;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import com.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.print.PrinterGraphics;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDao {
    //用户Dao类
    private JdbcUtil util = new JdbcUtil();

    //用户注册
    public Integer add(user user){
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = null;
        String sql = "insert into user()values(?,?,?,?,?,?)";
        Integer result = 0;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setInt(1,0);
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getSex());
            ps.setString(5,user.getArea());
            ps.setString(6,user.getBelong());//belong 为 user 中拿到的 belong
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result; //返回 1 则插入成功
    }//返回 1 则注册成功
    //快速注册【通过 Listener 快速进行用户注册】
    public Integer add(user user,HttpServletRequest request){
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = null;
        String sql = "insert into user()values(?,?,?,?,?,?)";
        Integer result = 0;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setInt(1,user.getId());//默认是 0 , 数据库已设置主键自增
            ps.setString(2,user.getName());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getSex());
            ps.setString(4,user.getArea());
            ps.setString(5,user.getBelong());//belong 为 user 中拿到的 belong
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(request);
        }
        return result; //返回 1 则插入成功
    }//返回 1 则注册成功
    //注册简化模板
    public Integer register_Simplify(HttpServletRequest request, HttpServletResponse response)
    {
        user user = new user(0,request.getParameter("name"),request.getParameter("password"),request.getParameter("sex"),
                request.getParameter("province")+request.getParameter("city"),(request.getParameter("belong")));
        userDao dao = new userDao();
        //学生注册获取的参数必须是 0 ，教师注册获取的参数必须是 1
        return dao.add(user,request);//返回 1 表示注册成功
    }

    //用户登录
    public user login(String userName,String password){
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,password,sex,area,belong from user where name = ? and password = ?";
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs != null) {
                user user = new user(rs.getInt("id"),rs.getString("name"),
                        rs.getString("password"),rs.getString("sex"),
                        rs.getString("area"),rs.getString("belong"));
                return  user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return null;
    }//返回一个user对象，里面包含登录者的所有信息
    //用户快速登录【通过 Listener 快速进行用户登录】
    public user login(String userName,String password,HttpServletRequest request){
        JdbcUtil util = new JdbcUtil();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,password,sex,area,belong from user where name = ? and password = ?";
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if(rs != null) {
                user user = new user(rs.getInt("id"),rs.getString("name"),
                        rs.getString("password"),rs.getString("sex"),
                        rs.getString("area"),rs.getString("belong"));
                return  user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);// conn 复用
        }
        return null;
    }//返回一个user对象，里面包含登录者的所有信息

    //用户信息删除
    public Integer delete(Integer id){
         String sql = "delete from user where id = ?";
         PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setInt(1,id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return result;
    }//返回 1 则删除成功
    //用户快速删除【通过 Listener 快速进行用户删除】
    public Integer delete(Integer id,HttpServletRequest request){
        String sql = "delete from user where id = ?";
        PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setInt(1,id);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(request);
        }
        return result;
    }//返回 1 则删除成功

    //学生修改信息[无法修改 belong ]
    public Integer stuChange(user user){
        String sql = "update user set name = ?,password = ?,sex = ?,area = ? where id = ?";
        PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getArea());
            ps.setInt(5,user.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return  result;
    }//返回 1 则修改成功
    //学生快速修改信息【通过 Listener 快速进行用户修改】
    public Integer stuChange(user user,HttpServletRequest request){
        String sql = "update user set name = ?,password = ?,sex = ?,area = ? where id = ?";
        PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getArea());
            ps.setInt(5,user.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(request);
        }
        return  result;
    }//返回 1 则修改成功

    //教师修改信息[可修改belong]
    public Integer teaChange(user user){
        String sql = "update user set name = ?,password = ?,sex = ?,area = ?,belong = ? where id = ?";
        PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getArea());
            ps.setString(5,user.getBelong());
            ps.setInt(6, user.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close();
        }
        return  result;
    }//返回 1 则修改成功
    //教师快速修改信息【通过 Listener 快速进行用户修改】
    public Integer teaChange(user user,HttpServletRequest request){
        String sql = "update user set name = ?,password = ?,sex = ?,area = ?,belong = ? where id = ?";
        PreparedStatement ps = null;
        Integer result = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getArea());
            ps.setString(5,user.getBelong());
            ps.setInt(6, user.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(request);
        }
        return  result;
    }//返回 1 则修改成功

    //教师端查询所有学生/教师信息
    public List<user> findAll(String belong){
        List<user> result = null;
        String sql ="select id,name,sex,password,area from user where belong = ?";
        user user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setString(1,belong);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                user = new user();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSex(rs.getString("sex"));
                user.setPassword(rs.getString("password"));
                user.setArea(rs.getString("area"));
                user.setBelong(rs.getString("belong"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return result;
    }//返回一个 List 集合
    //教师端快速查询所有学生/教师信息【通过 Listener 快速进行用户查询】
    public List<user> findAll(String belong,HttpServletRequest request){
        List<user> result = null;
        String sql ="select id,name,sex,password,area from user where belong = ?";
        user user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setString(1,belong);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                user = new user(rs.getInt("id"),rs.getString("name"),
                        rs.getString("sex"),rs.getString("password"),
                        rs.getString("area"),rs.getString("belong"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);
        }
        return result;
    }//返回一个 List 集合

    //教师端查询所有人的信息
    public List<user> findAll(){
        List<user> result = null ;
        String sql ="select id,name,sex,password,area,belong from user";
        user user = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                user = new user();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setArea(rs.getString("area"));
                user.setBelong(rs.getString("belong"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return result;
    }//返回一个 List 集合
    //教师端快速查询所有人的信息【通过 Listener 快速进行用户查询】
    public List<user> findAll(HttpServletRequest request){
        List<user> result = null ;
        String sql ="select id,name,sex,password,area,belong from user";
        user user = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                user = new user(rs.getInt("id"),rs.getString("name"),
                        rs.getString("password"),rs.getString("sex"),
                        rs.getString("area"),rs.getString("belong"));
                result.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);
        }
        return result;
    }//返回一个 List 集合

    //教师端通过id查询个人信息
    public user findOne(Integer id){
        user user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,password,sex,area,belong from user where id = ?";
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            user = new user(rs.getInt("id"),rs.getString("name"),
                    rs.getString("password"),rs.getString("sex"),
                    rs.getString("area"),rs.getString("belong"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return  user;
    }//返回一个 user 对象，里面包含查询结果的所有信息
    //教师端通过id快速查询个人信息【通过 Listener 快速进行用户查询】
    public user findOne(Integer id,HttpServletRequest request){
        user user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select id,name,password,sex,area,belong from user where id = ?";
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            user = new user(rs.getInt("id"),rs.getString("name"),
                    rs.getString("password"),rs.getString("sex"),
                    rs.getString("area"),rs.getString("belong"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);
        }
        return  user;
    }//返回一个 user 对象，里面包含查询结果的所有信息
}

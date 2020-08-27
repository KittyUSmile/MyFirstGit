package com.Dao;

import com.entity.city;
import com.entity.province;
import com.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class areaDao {
    //籍贯dao类
    private JdbcUtil util = new JdbcUtil();
    //省份查询
    public List<province> queryProvinceList(){
        List<province> provinces = new ArrayList<>();
        String sql = "select id,name,abbr,mainCity from provinces order by id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        province p = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                p = new province();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setMainCity(rs.getString("mainCity"));
                p.setAbbr(rs.getString("abbr"));
                provinces.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return provinces;
    }
    //快速省份查询
    public List<province> queryProvinceList(HttpServletRequest request){
        List<province> provinces = new ArrayList<>();
        String sql = "select id,name,abbr,mainCity from provinces order by id";
        PreparedStatement ps = null;
        ResultSet rs = null;
        province p = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                p = new province();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setMainCity(rs.getString("mainCity"));
                p.setAbbr(rs.getString("abbr"));
                provinces.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);
        }
        return provinces;
    }

    //城市查询
    public List<city> queryCityList(Integer provinceId){
        List<city> cities = new ArrayList<>();
        String sql = "select id,name from cities where provinceId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        city c = null;
        try {
            ps = util.getCon().prepareStatement(sql);
            ps.setInt(1,provinceId);
            rs = ps.executeQuery();
            while(rs.next()){
                c = new city();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                cities.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close();
        }
        return cities;
    }
    //快速城市查询
    public List<city> queryCityList(Integer provinceId,HttpServletRequest request){
        List<city> cities = new ArrayList<>();
        String sql = "select id,name from cities where provinceId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        city c = null;
        try {
            ps = util.getCon(request).prepareStatement(sql);
            ps.setInt(1,provinceId);
            rs = ps.executeQuery();
            while(rs.next()){
                c = new city();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                cities.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            util.close(rs);
            util.close(request);
        }
        return cities;
    }
}

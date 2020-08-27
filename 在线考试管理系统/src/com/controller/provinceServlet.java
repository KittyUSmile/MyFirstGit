package com.controller;

import com.Dao.areaDao;
import com.entity.province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class provinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //级联省份查询
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        areaDao dao = new areaDao();
        //把所有的省份信息装进一个List
        List<province> provinces = dao.queryProvinceList(request);
        //把List转换成Json格式，输出给AJAX请求
        if(provinces!=null){
            //调用jackson库，实现List->json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out =  response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}

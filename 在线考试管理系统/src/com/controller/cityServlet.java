package com.controller;

import com.Dao.areaDao;
import com.entity.city;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class cityServlet extends HttpServlet {
    //级联城市查询
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        String strProvinceId = request.getParameter("pId");
        //城市所属省份id不空
        if (strProvinceId != null && !"".equals(strProvinceId.trim())) {
            areaDao dao = new areaDao();
            List<city> cList = dao.queryCityList(Integer.valueOf(strProvinceId),request);
            //List转换为json
            if(cList!=null){
                ObjectMapper om = new ObjectMapper();
                json = om.writeValueAsString(cList);
            }
        }
        //输出数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

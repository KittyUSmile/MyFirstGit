package com.controller;

import com.Dao.userDao;
import com.entity.user;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class find_Stu_or_TeaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //教师端查询所有学生/教师用户: 需要在请求参数中获取一个 belong
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        userDao dao = new userDao();
        List<user> user = dao.findAll((request.getParameter("belong")),request);
        if(user!=null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(user);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}

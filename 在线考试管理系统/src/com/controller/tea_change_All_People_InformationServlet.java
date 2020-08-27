package com.controller;

import com.Dao.userDao;
import com.entity.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class tea_change_All_People_InformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //教师修改所有人信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        Integer result = null;
        user user = null ;
        userDao dao = new userDao();
        user = new user(Integer.valueOf(request.getParameter("id")),request.getParameter("name"),request.getParameter("password"),
                request.getParameter("sex"),request.getParameter("province")+request.getParameter("city"), request.getParameter("belong"));
        result = dao.teaChange(user,request);
        PrintWriter out = response.getWriter();
        out.println(result);//修改成功输出 1 ，修改失败输出 0
    }
}

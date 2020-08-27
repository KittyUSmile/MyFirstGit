package com.controller;

import com.Dao.userDao;
import com.entity.user;
import com.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class stu_Change_InformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //学生修改自己信息[登录成功后]
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        user user = null;
        Integer result = null;
        userDao dao = new userDao();
        user = new user(Integer.valueOf(request.getParameter("id")),request.getParameter("name"),request.getParameter("password"),
                request.getParameter("sex"),request.getParameter("province")+request.getParameter("city"),"0");
        result = dao.stuChange(user,request);
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}   //输出 1 则修改成功

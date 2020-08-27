package com.controller;

import com.Dao.userDao;
import com.entity.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class student_RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //学生注册 belong 必须为 0
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer result = null;
//        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        userDao dao = new userDao();
        result = dao.register_Simplify(request,response);
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}   //返回 1 则注册成功

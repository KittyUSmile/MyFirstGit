package com.controller;

import com.Dao.titleDao;
import com.entity.title;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class change_Title_InformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //修改题目
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        // titleId 需要需要一个参数，否则报 数字转换异常
        title title = new title(Integer.valueOf(request.getParameter("titleId")),request.getParameter("title"),
                request.getParameter("optionA"),request.getParameter("optionB"),
                request.getParameter("optionC"),request.getParameter("answer"),request.getParameter("optionD"));
        titleDao dao = new titleDao();
        Integer result = dao.titleChange(title,request);
        PrintWriter out = response.getWriter();
        out.println(result);//返回 1 则修改成功
    }
}

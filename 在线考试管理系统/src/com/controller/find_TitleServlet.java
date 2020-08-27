package com.controller;

import com.Dao.titleDao;
import com.entity.title;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class find_TitleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //通过id查询题目
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String json = "{}";
        titleDao dao = new titleDao();
        title title = null;
        title = dao.findTitle(Integer.valueOf(request.getParameter("titleId")),request);
        if(title!=null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(title);
        }
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}   //返回一个 json 格式的数据

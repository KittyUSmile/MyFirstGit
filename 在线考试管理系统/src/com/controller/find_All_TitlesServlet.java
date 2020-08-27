package com.controller;

import com.Dao.titleDao;
import com.entity.title;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class find_All_TitlesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //查询所有题目
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if(request.getSession(false)!= null){
            String json = "{}";
            titleDao dao = new titleDao();
            List<title> titles = null;
            titles = dao.findAllTitles(request);
            if(titles!=null){
                //打包送给json打回
                ObjectMapper om = new ObjectMapper();
                json = om.writeValueAsString(titles);
            }
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
//        }else{
//            response.sendRedirect("/loginError.jsp");
//        }
    }
}

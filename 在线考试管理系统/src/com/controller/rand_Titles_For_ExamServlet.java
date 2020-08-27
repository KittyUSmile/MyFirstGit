package com.controller;

import com.Dao.titleDao;
import com.entity.title;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class rand_Titles_For_ExamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String json = "{}";
        JdbcUtil util = new JdbcUtil();
        List<title> titles = null;
        titleDao dao = new titleDao();
        titles = dao.randTitles(request);
        //拿到私人储物柜，将考试题目放入
        HttpSession session = request.getSession(false);
        if(session!=null) session.setAttribute("titleKey",titles);
        //重新装 List ,防止某些人从 F12 中查看答案
        List<title> newList= new ArrayList<>();
        for(title title : titles){
             new title(title.getTitleId(),title.getTitle(),title.getOptionA(),title.getOptionB(),
                     title.getOptionC(),title.getOptionD(),null);
             newList.add(title);
        }
        ObjectMapper om = new ObjectMapper();
        json = om.writeValueAsString(newList);
        out.println(json);
        //试题打回，开始考试
        if(session!=null)session.setAttribute("beginTimeKey",System.currentTimeMillis());
        out.flush();
        out.close();
    }
}

package com.controller;

import com.entity.title;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class exam_GradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    //返回成绩 判断考试是否超时
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        //结束考试[考试时间若大于 30 分钟]
        if(session!=null){
            if( (System.currentTimeMillis() - (long)session.getAttribute("beginTimeKey")) / (1000*60*30) > 1 ){
            //作答超时，拒绝提交
            out.println(-1);
            }
            else {
                List<title> titles = (ArrayList) session.getAttribute("titleKey");
                Integer grade = 0;
                for (title title : titles) {
                    String userAnswer = request.getParameter("answer" + title.getTitleId());
                    if (title.getAnswer().equals(userAnswer)) {
                        grade += 25;
                    }
                    out.println(grade);
                    out.close();
                }
            }
        }
    }
}

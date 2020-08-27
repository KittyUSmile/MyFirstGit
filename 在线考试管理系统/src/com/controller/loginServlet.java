package com.controller;

import com.Dao.userDao;
import com.entity.user;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class loginServlet extends HttpServlet {
    //用户登录
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
         String json = "{}";
         String userName,password;
         userDao dao = new userDao();
         user user = dao.login(request.getParameter("userName"),request.getParameter("password"),request);
         //user != null 则查询成功，允许登录
        if(user!=null){
            //把用户信息装进全局作用域对象里
            request.getServletContext().setAttribute("loginKey",1);
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(user);
        }
        PrintWriter out = response.getWriter();
        out.println(json);//登陆成功则返回一个装着登录用户信息的 json 格式数据，否则为 json 为 null
    }
}

package com.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.http.HttpResponse;

public class oneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转为子接口
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        servletResponse.setContentType("text/html;charset=utf-8");
        String uri = request.getRequestURI();
        int idx = uri.lastIndexOf("/");
        String endWith = uri.substring(idx + 1);
        //用全局作用域对象
        ServletContext application =  request.getServletContext();
        Integer loginKey = (Integer) application.getAttribute("loginKey");
        //indexOf中加入登录和注册页面名称,除此之外全部拦截
        if(!endWith.equals("login.jsp")){
            if( loginKey == null){//无登录记录
                //回到登陆页面
                response.sendRedirect("login.jsp");
            }else {
                //登录了，放行
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else {
            //是访问登陆页面，放行
            filterChain.doFilter(servletRequest,servletResponse);
        }
//        else if (uri.indexOf("login")!= -1||uri.indexOf("register")!= -1||uri.indexOf("main")!= -1||"/myweb/".equals(uri)){
            //允许访问登录和注册页面
        //恶意登录打回
    }
}

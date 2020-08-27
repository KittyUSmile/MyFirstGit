package com.listener;

import com.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class connListener implements ServletContextListener {
    //监听器接口

    @Override//全局作用域创建时
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil util = new JdbcUtil();
        //创建一个map集合装conn对象
        Map map = new HashMap();
        //创建 20 个 conn对象
        for(int i = 0;i<20;i++){
            Connection conn = util.getCon();
            System.out.println("我"+conn+"出生了！");
            map.put(conn,true);//装进map,true表示此个 conn 连接通道空闲中
        }
        ServletContext application = sce.getServletContext();
        application.setAttribute("connKey",map);//装进全局作用域对象中
    }

    @Override//全局作用域销毁时
    public void contextDestroyed(ServletContextEvent sce) {
          JdbcUtil util = new JdbcUtil();
          ServletContext application = sce.getServletContext();
          Map map = (Map)application.getAttribute("connKey");
          //把 key 迭代
          Iterator iter = map.keySet().iterator();
          while(iter.hasNext()){
              Connection conn = (Connection) iter.next();
              if (conn!=null) {
                  System.out.println("我"+conn+"还会回来的!");
                  util.close(conn);
              }
          }
    }
}

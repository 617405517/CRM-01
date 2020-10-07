package com.bjpowernode.crm.web.filter;

import com.bjpowernode.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public  class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("过滤器启动");

        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();

        if ("/login.jsp".equals(path) || "/login/login.do".equals(path)){
            chain.doFilter(req,resp);
        }else {
            User myUser = (User) request.getSession().getAttribute("myUser");
            if (myUser != null){
                chain.doFilter(req,resp);
            }else {
                System.out.println(request.getContextPath());
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }


    }

    @Override
    public void destroy() {

    }
}

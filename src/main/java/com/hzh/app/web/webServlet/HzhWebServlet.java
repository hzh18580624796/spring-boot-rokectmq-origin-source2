package com.hzh.app.web.webServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hzhWebServlet")
public class HzhWebServlet extends HttpServlet {

    public HzhWebServlet(){
        System.out.println(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.getWriter().write("HzhWebServlet");
    }
}

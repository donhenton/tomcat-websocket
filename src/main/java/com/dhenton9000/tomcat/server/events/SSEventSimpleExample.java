/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.tomcat.server.events;

/**
 *
 * @author dhenton
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://milestonenext.blogspot.de/2013/07/html5-server-sent-events-sample-with.html
 *
 * @author swang
 *
 */
@WebServlet("/SSEventSimple")
public class SSEventSimpleExample extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
  // Besides "text/event-stream;", Chrome also needs charset, otherwise
        // does not work
        // "text/event-stream;charset=UTF-8"
        response.setContentType("text/event-stream;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");

        PrintWriter out = response.getWriter();

        while (true) {
            out.print("id: " + "ServerTime" + "\n");
            out.print("data: " + new Date().toLocaleString() + "\n\n");
            out.flush();
            // out.close(); //Do not close the writer!
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

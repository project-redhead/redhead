package xyz.dommi.servlets;

import xyz.dommi.requests.RequestManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gets Requests and redirects them to the RequestManager
 * Optional : Redirect the Client to a relative Path of the Website
 */
@WebServlet(name = "RequestServlet",value = "/request")
public class RequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect = (String) request.getParameter("redirect");
        new RequestManager().handleRequest(request,response);
        if(redirect != null && !redirect.equals("")){
            request.getServletContext().getRequestDispatcher(redirect).forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

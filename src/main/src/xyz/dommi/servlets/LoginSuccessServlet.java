package xyz.dommi.servlets;

import bell.oauth.discord.domain.User;
import bell.oauth.discord.main.Response;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/finishedlogin", name = "Finished Login")
public class LoginSuccessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        Response res = LoginServlet.builder.exchange(code);

        if (res == Response.ERROR) {
           System.out.println("Error");
        } else {
            User user =  LoginServlet.builder.getUser();
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            dbConnection.close();
        }
    }
}

package xyz.dommi.servlets;

import bell.oauth.discord.domain.User;
import bell.oauth.discord.main.Response;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mongodb.DB;
import xyz.dommi.beans.UserBean;
import xyz.dommi.common.EnvironmentConfig;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
            return;
        }

        // Otherwise create user
        User user = LoginServlet.builder.getUser();

        // Create JWT token and pass it to response cookies
        Algorithm algorithm = Algorithm.HMAC256(EnvironmentConfig.getSecretKey());
        String jwt = JWT.create()
                .withIssuer("discord")
                .withAudience("redhead")
                .withSubject(user.getId())
                .sign(algorithm);

        response.addCookie(new Cookie(HttpUtils.TOKEN_COOKIE_NAME, jwt));

        // Establish DB connection and add user
        DB db = DBConnection.getInstance().connect();
        UserDB userDB = new UserDB(db);
        userDB.createUser(user.getId(), user.getUsername(), user.getEmail());
        UserBean userBean = new UserBean(user.getId());
        request.setAttribute("user", userBean);
        request.getServletContext().getRequestDispatcher("/").forward(request, response);
    }
}

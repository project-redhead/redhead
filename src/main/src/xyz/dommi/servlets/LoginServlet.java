package xyz.dommi.servlets;

import bell.oauth.discord.main.OAuthBuilder;
import xyz.dommi.common.EnvironmentConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login to the Website by OAuth
 */
@WebServlet(value = "/login",name = "Login")
public class LoginServlet extends HttpServlet {

    /**
     * Creating link for Discord Login
     */
    public static OAuthBuilder builder = new OAuthBuilder(
            EnvironmentConfig.getDiscordConfig().getClientId(),
            EnvironmentConfig.getDiscordConfig().getClientSecret())
                .setScopes(new String[]{"identify","email"})
                .setRedirectURI(EnvironmentConfig.getDiscordConfig().getRedirectUrl());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authURL = builder.getAuthorizationUrl(null);
        response.sendRedirect(authURL);
    }
}

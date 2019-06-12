package xyz.dommi.servlets;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserRequestServlet",value = "/request")
public class UserRequestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        System.out.println(type);
        DBConnection dbConnection = new DBConnection();
        DB db = dbConnection.connect();
        UserDB userDB = new UserDB(db);
        switch (type){
            case "addGame":
                String id = request.getParameter("id");
                String des = request.getParameter("des");
                List<String> list = new ArrayList<>();
                list.add("Ja");
                list.add("Nein");
                userDB.addGame(id,des,list);
                break;
            case "addBet":
                String id2 = request.getParameter("id");
                String gameid = request.getParameter("gameid");
                int amount = Integer.valueOf(request.getParameter("amount"));
                int option = Integer.valueOf(request.getParameter("option"));
                userDB.addBet(id2,gameid,amount,option);
                break;
            default:
                request.getServletContext().getRequestDispatcher("/404").forward(request,response);
                return;
        }
        request.getServletContext().getRequestDispatcher("/"+request.getParameter("redirect")).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

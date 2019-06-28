package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class CreateBet extends JsonRequest {

    public CreateBet(RequestManager manager){
        super("CreateBet", manager);
    }

    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String gameId = request.getParameter("gameId");
        String amount = request.getParameter("amount");
        String option = request.getParameter("option");

        if (id == null || id.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "Id cannot be empty!");
        }
        if (gameId == null || gameId.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "GameId cannot be empty!");
        }
        try {
            int iamount = Integer.valueOf(amount);
            int ioption = Integer.valueOf(option);
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            UserDB userDB = new UserDB(db);
            return userDB.addBet(id,gameId,iamount,ioption);
        } catch (Exception e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }

}

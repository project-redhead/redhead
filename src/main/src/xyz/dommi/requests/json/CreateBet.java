package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Requests the creation of a new Bet
 */
public class CreateBet extends JsonRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public CreateBet(RequestManager manager){
        super("CreateBet", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return id of the created Bet
     */
    public Response handleRequest(HttpServletRequest request) {
        String id = HttpUtils.getUserIdFromJwt(request);
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
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            return userDB.addBet(id,gameId,iamount,ioption);
        } catch (Exception e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }

}

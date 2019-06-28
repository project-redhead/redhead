package xyz.dommi.requests.json;

import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class BetInfo extends JsonRequest {

    public BetInfo(RequestManager manager) {
        super("BetInfo", manager);
    }

    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String gameId = request.getParameter("gameId");

        if (gameId != null && !gameId.equals("")) {
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            BetGameDB gameDB = new BetGameDB(db);
            DBObject bet = null;

            if (id != null && !id.equals("")) {
                bet = gameDB.getBet(gameId, id);
            } else if (userId != null && !userId.equals("")) {
                bet = gameDB.getBetbyUser(gameId, userId);
            }

            if (bet != null) {
                return new Response(ResponseType.OK, bet);
            } else {
                return new Response(ResponseType.ERROR, "Id not valid");
            }
        }
        return new Response(ResponseType.ERROR, "GameId not valid");
    }
}
package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Requests all existing Games
 */
public class GameList extends JsonRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public GameList(RequestManager manager){
        super("GameList", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return List of Games
     */
    public Response handleRequest(HttpServletRequest request){
            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);

            return new Response(ResponseType.OK, gameDB.getGames());
    }
}
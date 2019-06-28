package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class GameList extends JsonRequest {

    public GameList(RequestManager manager){
        super("GameList", manager);
    }
    public Response handleRequest(HttpServletRequest request){
            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);

            return new Response(ResponseType.OK, gameDB.getGames());
    }
}
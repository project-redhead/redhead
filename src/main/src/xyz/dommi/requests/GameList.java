package xyz.dommi.requests;

import com.mongodb.DB;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;

import javax.servlet.http.HttpServletRequest;

public class GameList extends Request{

    public GameList(RequestManager manager){
        super("GameList", manager);
    }
    public Response handleRequest(HttpServletRequest request){
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            BetGameDB gameDB = new BetGameDB(db);

            return new Response(ResponseType.OK, gameDB.getGames());
    }
}
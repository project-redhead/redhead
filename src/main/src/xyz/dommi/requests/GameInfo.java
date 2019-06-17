package xyz.dommi.requests;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.DBManager;

import javax.servlet.http.HttpServletRequest;

public class GameInfo extends Request{

    public GameInfo(RequestManager manager){
        super("GameInfo", manager);
    }
    public Response handleRequest(HttpServletRequest request){
        String id = request.getParameter("id");

        if(id != null && !id.equals("")){
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            BetGameDB gameDB = new BetGameDB(db);
            DBObject game = gameDB.getGame(id);
            if(game != null){
                return new Response(ResponseType.OK, DBManager.objectIDToID((BasicDBObject) game));
            }
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}
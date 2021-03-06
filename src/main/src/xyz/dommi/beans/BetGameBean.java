package xyz.dommi.beans;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.DBManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

/**
 * Creates a new Game or get a existing Game from the Database
 */

public class BetGameBean {
    private String id;
    private String userId;
    private int answer;

    public BetGameBean(){

    }


    /**
     * @param id Id of the Game
     * @param userId actual Client
     */
    public BetGameBean(String id, String userId) {
        this.id = id;
        this.userId = userId;
        if((id != null && !id.equals(""))){
            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);
            this.answer = gameDB.getAnswer(id);
        }
    }


    /**
     * @return Game or throws an exception if the Game does not exist
     */
    public String getInfo(){
        if(id != null && !id.equals("")){
            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);
            DBObject game = gameDB.getGame(id);
            if(game != null){
                return new Response(ResponseType.OK, DBManager.objectIDToID((BasicDBObject) game)).toString();
            }
        }
        return new Response(ResponseType.ERROR,"Id not valid").toString();
    }

    /**
     * @return a list of all existing Games
     */
    public String getGameList(){
        DB db = DBConnection.getInstance().connect();
        BetGameDB gameDB = new BetGameDB(db);

        return new Response(ResponseType.OK, gameDB.getGames()).toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getAnswer() {
        return answer;
    }

    /**
     * @param answer Clients chosen answer
     */
    public void setAnswer(int answer) {
        this.answer = answer;
        DB db = DBConnection.getInstance().connect();
        BetGameDB gameDB = new BetGameDB(db);
        gameDB.setAnswer(id,userId,answer);

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

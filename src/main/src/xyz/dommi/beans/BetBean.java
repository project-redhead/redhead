package xyz.dommi.beans;

import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

/**
 * Creates a new Bet or get a existing Bet from the Database
 */

public class BetBean {

    private String gameID;
    private String betID;

    /**
     * @param gameID Id of the related Game
     * @param betID Id of the Bet
     */
    public BetBean(String gameID, String betID) {
        this.gameID = gameID;
        this.betID = betID;
    }

    /**
     * @return the Bet or throws an exception if the Bet does not exist
     */
    public String getInfo(){
        DB db = DBConnection.getInstance().connect();
        BetGameDB gameDB = new BetGameDB(db);
        DBObject bet = gameDB.getBet(gameID, betID);

        if (bet != null) {
            return new Response(ResponseType.OK, bet).toString();
        } else {
            return new Response(ResponseType.ERROR, "Id not valid").toString();
        }
    }

    public String getBetID() {
        return betID;
    }

    public void setBetID(String betID) {
        this.betID = betID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}

package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.json.JSONArray;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import java.util.Date;
import java.util.List;

/**
 * Manages the user operations
 */
public class UserDB extends DBManager {

    public UserDB(DB db) {
        super(db, "Users");
    }

    public DBObject getUser(String id) {
        return getObjectByID(id);
    }

    public String getName(String id) {
        return getStringByID(id, "name");
    }

    public String getEmail(String id) {
        return getStringByID(id, "email");
    }

    public String getRoleID(String id) {
        return getStringByID(id, "roleID");
    }

    public int getPoints(String id) {
        return getIntByID(id, "points");
    }

    public Date getLastReward(String id){
        return getDateByID(id, "reward");
    }

    public List<DBObject> getUsers() {
        return getDBObjectListByCollection();
    }

    public void setLastReward(String id, Date date){
        setDateByID(id,"reward",date);
    }

    public void setName(String id, String name) {
        setStringByID(id, "name", name);
    }

    public void setEmail(String id, String email) {
        setStringByID(id, "email", email);
    }

    public void setRoleID(String id, String roleID) {
        setStringByID(id, "roleID", roleID);
    }

    public void setPoints(String id, int points) {
        setIntByID(id, "points", points);
    }

    /**
     * @param id ID of the user
     * @param points amount of points
     * @param description description of the transaction
     */
    public void addPoints(String id, int points, String description){
        setPoints(id, getPoints(id)+points);
        addTransaction(id, points, description);
    }

    /**
     * @param id ID of the user
     * @param points amount of points
     */
    public void addPoints(String id, int points){
        setPoints(id, getPoints(id)+points);
    }

    /**
     * @param id ID of the user
     * @param points amount of points
     * @param description Description of the transaction
     * @return If amount of points is higher than the users points return false otherwise return true
     */
    public boolean remPoints(String id, int points, String description){
        if(getPoints(id) < points){
            return false;
        }
        setPoints(id, getPoints(id)-points);
        addTransaction(id, -points, description);
        return true;
    }

    /**
     * @param id ID of the user
     * @param points
     * @return If amount of points is higher than the users points return false otherwise return true
     */
    public boolean remPoints(String id, int points){
        if(getPoints(id) < points){
            return false;
        }
        setPoints(id, getPoints(id)-points);
        return true;
    }

    /**
     * @param id ID of the user
     * @return Response with new amount of points or throws an error response if not enough time expired
     */
    public Response collectReward(String id){
        Date last = getLastReward(id);
        Date next = addHoursToJavaUtilDate(last,24);
        if(new Date().after(next)){
            double maxPoints = 1000;
            int points = (int)(maxPoints * Math.random());

            addPoints(id,points,"Tägliche Belohnung");
            setLastReward(id, new Date());
            return new Response(ResponseType.OK,""+points);
        }

        return new Response(ResponseType.ERROR,"Du kannst die Belohnung nur alle 24 Stunden einlösen!");
    }

    /**
     * @param id ID of the user
     * @param gameid ID of the game
     * @param amount Amount of bet points
     * @param option picked option
     * @return Response if the bet could be set or error response if you do not have enough points, you bet less than 1 point, you picked an invalid option, you already made a bet or you are the creator, the bet time has ended
     */
    public Response addBet(String id, String gameid, int amount, int option) {
        BetGameDB gameDB = new BetGameDB(db);
        if (gameDB.isBetTimeValid(gameid)) {
            if (gameDB.isUserValid(gameid, id)) {
                if (option >= 0 && option < gameDB.getOptions(gameid).length) {
                    if (amount > 0) {
                        if (getPoints(id) >= amount) {
                            remPoints(id, amount, "Wette eingereicht.");

                            return new Response(ResponseType.OK, new BasicDBObject("_id", gameDB.addBet(gameid, id, amount, option)));
                        }
                        return new Response(ResponseType.ERROR, "You do not have enough Points!");
                    }
                    return new Response(ResponseType.ERROR, "You can not bet less than 1 Point!");
                }
                return new Response(ResponseType.ERROR, "That option is not valid!");
            }
            return new Response(ResponseType.ERROR, "You already made a Bet or you are the creator!");
        }
        return new Response(ResponseType.ERROR, "The Bet time has ended!");
    }

    /**
     * @param id ID of the game
     * @param description Description of the game
     * @param options List of bet options
     * @return true if all went through
     */
    public boolean addGame(String id, String description, List<String> options) {
        BetGameDB gameDB = new BetGameDB(db);
        gameDB.createBetGame(description, id, options, null);
        return true;
    }

    /**
     * @param id ID of the transaction
     * @param value value of the transaction
     * @param description Description of the transaction
     */
    public void addTransaction(String id, int value, String description){

        int _id = getTransactions(id).length();

        BasicDBObject transaction = new BasicDBObject("_id", _id)
                .append("description", description)
                .append("value", value);

        pushDBObjectByID(id, "transactions", transaction);
    }

    /**
     * @param id ID of the transaction
     * @return transaction as JSONArray
     */
    public JSONArray getTransactions(String id) {
        return getJSONArrayByID(id, "transactions");
    }

    /**
     * Checks if user exists
     * @param id ID of the user
     * @return true if user exists otherwise false
     */
    public boolean userExists(String id) {
        return getObjectByID(id) != null;
    }

    /**
     * Creates a user
     * @param id ID of the user
     * @param name Name of the user
     * @param email Email of the user
     */
    public void createUser(String id, String name, String email) {
        if (!userExists(id)) {
            DBObject user = new BasicDBObject("_id", id)
                    .append("name", name)
                    .append("email", email)
                    .append("points", 100)
                    .append("roleid", "user")
                    .append("reward", new Date())
                    .append("transactions", new BasicDBList());
            getCollection().insert(user);
        }

    }

}

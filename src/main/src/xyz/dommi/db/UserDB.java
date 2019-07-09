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

    public void addPoints(String id, int points, String description){
        setPoints(id, getPoints(id)+points);
        addTransaction(id, points, description);
    }

    public void addPoints(String id, int points){
        setPoints(id, getPoints(id)+points);
    }

    public boolean remPoints(String id, int points, String description){
        if(getPoints(id) < points){
            return false;
        }
        setPoints(id, getPoints(id)-points);
        addTransaction(id, -points, description);
        return true;
    }

    public boolean remPoints(String id, int points){
        if(getPoints(id) < points){
            return false;
        }
        setPoints(id, getPoints(id)-points);
        return true;
    }

    public Response collectReward(String id){
        Date last = getLastReward(id);
        Date next = addHoursToJavaUtilDate(last,24);
        if(new Date().after(next)){
            double maxPoints = 1000;
            int points = (int)(maxPoints * Math.random());

            addPoints(id,points,"");
            return new Response(ResponseType.OK,""+points);
        }

        return new Response(ResponseType.ERROR,"You can only claim this after 24h");
    }

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

    public boolean addGame(String id, String description, List<String> options) {
        BetGameDB gameDB = new BetGameDB(db);
        gameDB.createBetGame(description, id, options, null);
        return true;
    }

    public void addTransaction(String id, int value, String description){

        int _id = getTransactions(id).length();

        BasicDBObject transaction = new BasicDBObject("_id", _id)
                .append("description", description)
                .append("value", value);

        pushDBObjectByID(id, "transactions", transaction);
    }

    public JSONArray getTransactions(String id) {
        return getJSONArrayByID(id, "transactions");
    }

    public boolean userExists(String id) {
        return getObjectByID(id) != null;
    }

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

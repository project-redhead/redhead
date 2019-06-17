package xyz.dommi.db;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

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

    public Response addBet(String id, String gameid, int amount, int option) {
        BetGameDB gameDB = new BetGameDB(db);
        if (gameDB.isBetTimeValid(gameid)) {
            if (gameDB.isUserValid(gameid, id)) {
                if (amount > 0) {
                    if (getPoints(id) >= amount) {
                        setPoints(id, getPoints(id) - amount);

                        return new Response(ResponseType.OK, new BasicDBObject("_id",gameDB.addBet(gameid, id, amount, option)));
                    }
                    return new Response(ResponseType.ERROR, "You do not have enough Points!");
                }
                return new Response(ResponseType.ERROR, "You can not bet less than 1 Point!");
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

    public boolean userExists(String id) {
        return getObjectByID(id) != null;
    }

    public void createUser(String id, String name, String email) {
        if (!userExists(id)) {
            DBObject user = new BasicDBObject("_id", id)
                    .append("name", name)
                    .append("email", email)
                    .append("points", 0)
                    .append("roleid", "user");
            getCollection().insert(user);
        }

    }

}

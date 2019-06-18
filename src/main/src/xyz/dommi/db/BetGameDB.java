package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import java.util.Date;
import java.util.List;

public class BetGameDB extends DBManager {

    public BetGameDB(DB db) {
        super(db, "BetGames");
    }

    public DBObject getGame(String id) {
        return getObjectByID(id, true);
    }

    public String getDescription(String id) {
        return getStringByID(id, "description", true);
    }

    public Date getDate(String id) {
        return getDateByID(id, "date", true);
    }

    public Date getTimeLimit(String id) {
        return getDateByID(id, "timelimit", true);
    }

    public String getCreator(String id) {
        return getStringByID(id, "creator", true);
    }

    public String[] getOptions(String id) {
        return getStringArrayByID(id, "options", true);
    }

    public int getAnswer(String id) {
        try {
            return getIntByID(id, "answer", true);
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean isBetTimeValid(String id) {
        return (getTimeLimit(id) == null) || getTimeLimit(id).after(new Date());
    }

    public boolean isUserValid(String id, String userID) {
        if (userID.equalsIgnoreCase(getCreator(id))) {
            return false;
        }
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            String user = (String) bet.get("user");
            if (user.equalsIgnoreCase(userID)) {
                return false;
            }
        }
        return true;
    }

    public BasicDBList getGames() {
        List<DBObject> list = getDBObjectListByCollection();
        BasicDBList dbList = new BasicDBList();
        for (DBObject object : list) {
            dbList.add(objectIDToID((BasicDBObject) object));
        }
        return dbList;
    }

    public int getAmountbyOption(String id, int option) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            int betValue = (int) bet.get("option");
            if (option == betValue) {
                amount += (int) bet.get("amount");
            }
        }
        return amount;
    }

    public int getAmount(String id) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            amount += (int) bet.get("amount");
        }
        return amount;
    }

    public int getAmountWithoutOption(String id, int option) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            int betValue = (int) bet.get("option");
            if (option != betValue) {
                amount += (int) bet.get("amount");
            }
        }
        return amount;
    }

    public double getRatebyOption(String id, int option) {
        return (double) getAmountbyOption(id, option) / (double) getAmount(id);
    }

    public double getRateinOption(String id, int option, int amount) {
        return (double) amount / (double) getAmountbyOption(id, option);
    }

    public int getWinAmount(String id, int option, int amount) {
        return (int) (getAmountWithoutOption(id, option) * getRateinOption(id, option, amount)) + amount;
    }

    public Response setAnswer(String id, String userID, int value) {
        if (value >= 0 && value < getOptions(id).length) {
            if (getCreator(id).equalsIgnoreCase(userID)) {
                if (getAnswer(id) == -1) {
                    UserDB userDB = new UserDB(db);
                    BasicDBList bets = getDBListByID(id, "bets", true);
                    for (int i = 0; i < bets.size(); i++) {
                        BasicDBObject bet = (BasicDBObject) bets.get(i);
                        int betValue = (int) bet.get("option");
                        if (value == betValue) {
                            String user = (String) bet.get("user");
                            int amount = (int) bet.get("amount");
                            userDB.setPoints(user, userDB.getPoints(user) + getWinAmount(id, value, amount));
                        }
                    }
                }
                setValueByID(id, "answer", value, true);
                return new Response(ResponseType.OK, "Answer was set!");
            }
            return new Response(ResponseType.ERROR, "Only the Creator of the Game can do this!");
        }
        return new Response(ResponseType.ERROR, "That option is not valid!");
    }

    public JSONArray getBets(String id) {
        return getJSONArrayByID(id, "bets", true);
    }

    public BasicDBObject getBetbyUser(String id, String userID) {
        BasicDBList bets = (BasicDBList) getObjectByID(id, true).get("bets");
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            String user = (String) bet.get("user");
            if (user.equalsIgnoreCase(userID)) {
                return bet;
            }
        }
        return null;
    }

    public BasicDBObject getBet(String id, String betId) {
        BasicDBList bets = (BasicDBList) getObjectByID(id, true).get("bets");
        try {
            return (BasicDBObject) bets.get(betId);
        } catch (Exception e) {
            return null;
        }
    }

    public int addBet(String id, String userID, int amount, int option) {
        int _id = getBets(id).length();
        BasicDBObject bet = new BasicDBObject("_id", _id)
                .append("user", userID)
                .append("date", new Date())
                .append("amount", amount)
                .append("option", option);

        pushDBObjectByID(id, "bets", bet, true);
        return _id;
    }

    public void remBet(String id, String betid) {

        removeValueByID(id, "bets", betid);

    }

    public Response createBetGame(String description, String creatorId, List<String> options, Date timelimit) {
        BasicDBList dbOptions = new BasicDBList();
        dbOptions.addAll(options);
        DBObject object = new BasicDBObject()
                .append("description", description)
                .append("date", new Date())
                .append("timelimit", timelimit)
                .append("creator", creatorId)
                .append("bets", new BasicDBList())
                .append("options", dbOptions)
                .append("answer", null);
        getCollection().insert(object);
        return new Response(ResponseType.OK, new BasicDBObject("_id", objectIDToID((BasicDBObject) object).get("_id")));
    }
}

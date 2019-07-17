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

/**
 * Creates a bet game in the DB and manages the bets
 */
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

    /**
     * @param id GameID
     * @return Answer or throws an exception
     */
    public int getAnswer(String id) {
        try {
            return getIntByID(id, "answer", true);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * @param id GameID
     * @return true if bet time is valid otherwise false
     */
    public boolean isBetTimeValid(String id) {
        return (getTimeLimit(id) == null) || getTimeLimit(id).after(new Date());
    }

    /**
     * @param id GameID
     * @param userID Id of the user
     * @return false if the userID equals the creatorID or true if they are not equal
     */
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

    /**
     * @return List of all bet games
     */
    public BasicDBList getGames() {
        List<DBObject> list = getDBObjectListByCollection();
        BasicDBList dbList = new BasicDBList();
        for (DBObject object : list) {
            dbList.add(objectIDToID((BasicDBObject) object));
        }
        return dbList;
    }

    /**
     * @param id GameID
     * @param option ID of the selected option
     * @return the amount for the option
     */
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

    /**
     * @param id GameID
     * @return amount of the bet
     */
    public int getAmount(String id) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            amount += (int) bet.get("amount");
        }
        return amount;
    }

    /**
     * @param id GameID
     * @param option ID of the selected option
     * @return amount without option
     */
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

    /**
     * @param id GameID
     * @param option ID of the selected option
     * @return Total amount by option
     */
    public double getRatebyOption(String id, int option) {
        return (double) getAmountbyOption(id, option) / (double) getAmount(id);
    }

    /**
     * @param id GameID
     * @param option ID of the selected option
     * @param amount Set amount
     * @return Proportion of the total amount
     */
    public double getRateinOption(String id, int option, int amount) {
        return (double) amount / (double) getAmountbyOption(id, option);
    }

    /**
     * @param id GameID
     * @param option ID of the selected option
     * @param amount Set amount
     * @return Win amount
     */
    public int getWinAmount(String id, int option, int amount) {
        return (int) (getAmountWithoutOption(id, option) * getRateinOption(id, option, amount)) + amount;
    }

    /**
     * @param id GameID
     * @param userID ID of the user
     * @param value ID of the correct option
     * @return Response that the answer was set or throws an error response if the you are not the creator of the bet or the option is not valid
     */
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
                            userDB.addPoints(user, getWinAmount(id, value, amount), "Wettgewinn");
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

    /**
     * @param id GameID
     * @return JSON Array with the bets
     */
    public JSONArray getBets(String id) {
        return getJSONArrayByID(id, "bets", true);
    }

    /**
     * @param id GameID
     * @param userID ID of the user
     * @return the bet the user made
     */
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

    /**
     * @param id GameID
     * @param betId ID of the bet
     * @return the bet or throws an exception if the bet does not exist
     */
    public BasicDBObject getBet(String id, String betId) {
        BasicDBList bets = (BasicDBList) getObjectByID(id, true).get("bets");
        try {
            return (BasicDBObject) bets.get(betId);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param id GameID
     * @param userID Id of the betting user
     * @param amount Amount of points the user has bet
     * @param option The option the user picked
     * @return ID of the bet
     */
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

    /**
     * Removes a bet
     * @param id
     * @param betid Id of the bet game
     */
    public void remBet(String id, String betid) {

        removeValueByID(id, "bets", betid);

    }


    /**
     * Creates the bet game
     * @param description Given description for the new bet
     * @param creatorId ID of the creator
     * @param options Given options for the bet
     * @param timelimit date till the bet expires
     * @return Response with the created bet game
     */
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

package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.json.JSONArray;

import java.util.Date;
import java.util.List;

public class BetGameDB extends DBManager {

    public BetGameDB(DB db) {
        super(db, "BetGames");
    }

    public String getDescription(String id) {
        return getStringByID(id, "description",true);
    }

    public Date getDate(String id) {
        return getDateByID(id, "date",true);
    }
    public Date getTimeLimit(String id) {
        return getDateByID(id, "timelimit",true);
    }

    public String getCreator(String id) {
        return getStringByID(id, "creator",true);
    }

    public String[] getOptions(String id) {
        return getStringArrayByID(id, "options",true);
    }

    public int getAnswer(String id) {
        try {
            return getIntByID(id, "answer",true);
        }catch (NumberFormatException e){
            return -1;
        }
    }

    public boolean isBetTimeValid(String id){
        return (getTimeLimit(id) == null) || getTimeLimit(id).after(new Date());
    }
    public boolean isUserValid(String id, String userID){
        if (userID.equalsIgnoreCase(getCreator(id))){
            return false;
        }
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            String user = (String) bet.get("user");
            if(user.equalsIgnoreCase(userID)){
                return false;
            }
        }
        return true;
    }

    public BasicDBList getGames() {
        List<DBObject> list = getDBObjectListByCollection();
        BasicDBList dbList = new BasicDBList();
        for(DBObject object : list){
            ((BasicDBObject) object).replace("_id",((ObjectId) object.get("_id")).toString());
            dbList.add(object);
        }
        return dbList;
    }

    public int getAmountbyOption(String id, int option) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            int betValue = Integer.valueOf((String) bet.get("option"));
            if (option == betValue) {
                amount += Integer.valueOf((String) bet.get("amount"));
            }
        }
        return amount;
    }

    public int getAmount(String id) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            amount += Integer.valueOf((String) bet.get("amount"));
        }
        return amount;
    }

    public int getAmountWithoutOption(String id, int option) {
        int amount = 0;
        BasicDBList bets = getDBListByID(id, "bets", true);
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            int betValue = Integer.valueOf((String) bet.get("option"));
            if (option != betValue) {
                amount += Integer.valueOf((String) bet.get("amount"));
            }
        }
        return amount;
    }

    public double getRatebyOption(String id, int option) {
        return (double)getAmountbyOption(id,option) / (double)getAmount(id);
    }
    public double getRateinOption(String id, int option, int amount) {
        return (double)amount / (double)getAmountbyOption(id,option);
    }

    public int getWinAmount(String id, int option, int amount){
        return (int)(getAmountWithoutOption(id,option) * getRateinOption(id,option,amount)) + amount;
    }

    public void setAnswer(String id, int value) {
        if(getAnswer(id) == -1) {
            UserDB userDB = new UserDB(db);
            BasicDBList bets = getDBListByID(id, "bets", true);
            for (int i = 0; i < bets.size(); i++) {
                BasicDBObject bet = (BasicDBObject) bets.get(i);
                int betValue = Integer.valueOf((String) bet.get("option"));
                if (value == betValue) {
                    String user = (String) bet.get("user");
                    int amount = Integer.valueOf((String) bet.get("amount"));
                    userDB.setPoints(user, userDB.getPoints(user) + getWinAmount(id, value, amount));
                }
            }
        }
        setValueByID(id, "answer", value,true);
    }

    public JSONArray getBets(String id) {
        return getJSONArrayByID(id, "bets",true);
    }

    public BasicDBObject getBet(String id, String userID) {
        BasicDBList bets = (BasicDBList) getObjectByID(id).get("bets");
        for (int i = 0; i < bets.size(); i++) {
            BasicDBObject bet = (BasicDBObject) bets.get(i);
            String user = (String) bet.get("user");
            if (user.equalsIgnoreCase(userID)) {
                return bet;
            }
        }
        return null;
    }

    public void addBet(String id, String userID, int amount, int option) {
        BasicDBObject bet = new BasicDBObject("_id", getBets(id).length())
                .append("user", userID)
                .append("date", new Date())
                .append("amount", amount)
                .append("option", option);

        pushDBObjectByID(id,"bets", bet, true);
    }

    public void remBet(String id, String betid) {

        removeValueByID(id, "bets", betid);

    }

    public void createBetGame(String description, String creatorId, List<String> options, Date timelimit) {
        BasicDBList dbOptions = new BasicDBList();
        dbOptions.addAll(options);
        DBObject user = new BasicDBObject()
                .append("description", description)
                .append("date", new Date())
                .append("timelimit", timelimit)
                .append("creator", creatorId)
                .append("bets", new BasicDBList())
                .append("options", dbOptions)
                .append("answer", null);
        getCollection().insert(user);
    }
}

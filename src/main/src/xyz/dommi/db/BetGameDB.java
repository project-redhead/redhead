package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.Date;
import java.util.List;

public class BetGameDB extends DBManager {

    public BetGameDB(DB db) {
        super(db, "BetGames");
    }

    public String getDescription(String id) {
        return getStringByID(id,"description");
    }

    public Date getDate(String id) {
        return getDateByID(id,"date");
    }

    public String getCreator(String id) {
        return getStringByID(id,"creator");
    }

    public String[] getOptions(String id) {
        return getStringArrayByID(id,"options");
    }

    public int getAnswer(String id) {
        return getIntByID(id,"answer");
    }

    public void createBetGame(String description, String creatorId, List<String> options) {
        DBObject user = new BasicDBObject()
                .append("description", description)
                .append("date", new Date())
                .append("creator",creatorId)
                .append("options",new BasicDBList().addAll(options))
                .append("answer",null);
        getCollection().insert(user);
    }
}

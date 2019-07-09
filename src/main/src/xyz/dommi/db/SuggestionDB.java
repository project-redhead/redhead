package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import java.util.Date;
import java.util.List;

public class SuggestionDB extends DBManager{
    public SuggestionDB(DB db) {
        super(db, "Suggestions");
    }

    public DBObject getSuggestion(String id){
        return getObjectByID(id,true);
    }

    public BasicDBList getSuggestionList(){
        List<DBObject> list = getDBObjectListByCollection();
        BasicDBList dbList = new BasicDBList();
        for (DBObject object : list) {
            dbList.add(objectIDToID((BasicDBObject) object));
        }
        return dbList;
    }

    public void setRead(String id, boolean read){
        setBooleanByID(id, "read", read, true);
    }

    public Response createSuggestion(String userId, String content){
        DBObject object = new BasicDBObject()
                .append("userId", userId)
                .append("date", new Date())
                .append("content", content)
                .append("read", false);
        getCollection().insert(object);
        return new Response(ResponseType.OK, new BasicDBObject("_id", objectIDToID((BasicDBObject) object).get("_id")));
    }
}

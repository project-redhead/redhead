package xyz.dommi.db;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

public abstract class DBManager {
    protected DB db;
    protected String tablename;

    public DBManager(DB db, String tablename) {
        this.db = db;
        this.tablename = tablename;
        init();
    }

    protected void init() {
        if (getCollection() == null) {
            db.createCollection(tablename, null);
        }
    }

    protected DBCollection getCollection() {
        return db.getCollection(tablename);
    }

    protected DBObject getObjectByID(String id, boolean objectID) {
        if(objectID){
            return getCollection().findOne(new BasicDBObject("_id", new ObjectId(id)));
        }
        return getCollection().findOne(new BasicDBObject("_id",id));
    }
    protected DBObject getObjectByID(String id) {
        return getCollection().findOne(new BasicDBObject("_id",id));
    }

    protected Object getValueByID(String id, String key, boolean objectID) {
        return getObjectByID(id, objectID).get(key);
    }
    protected Object getValueByID(String id, String key) {
        return getObjectByID(id).get(key);
    }

    protected String getStringByID(String id, String key) {
        return (String) getValueByID(id, key);
    }

    protected String getStringByID(String id, String key, boolean objectID) {
        return (String) getValueByID(id, key, objectID);
    }

    protected BasicDBObject getDBObjectByID(String id, String key) {
        return (BasicDBObject) getValueByID(id, key);
    }

    protected BasicDBList getDBListByID(String id, String key) {
        return (BasicDBList) getValueByID(id, key);
    }

    protected BasicDBList getDBListByID(String id, String key, boolean objectID) {
        return (BasicDBList) getValueByID(id, key,objectID);
    }

    protected String[] getStringArrayByID(String id, String key) {
        return (String[]) getValueByID(id, key);
    }

    protected String[] getStringArrayByID(String id, String key, boolean objectID) {
        return (String[]) getValueByID(id, key,objectID);
    }

    protected JSONObject getJSONByID(String id, String key) {
        return new JSONObject(JSON.serialize(getObjectByID(id).get(key)));
    }

    protected JSONArray getJSONArrayByCollection() {
        return new JSONArray(getCollection().find().toArray());
    }

    protected List<DBObject> getDBObjectListByCollection() {
        return getCollection().find().toArray();
    }

    protected JSONArray getJSONArrayByID(String id, String key) {
        return new JSONArray(JSON.serialize(getObjectByID(id).get(key)));
    }

    protected JSONArray getJSONArrayByID(String id, String key, boolean objectID) {
        return new JSONArray(JSON.serialize(getObjectByID(id,objectID).get(key)));
    }

    protected Date getDateByID(String id, String key) {
        return (Date) getValueByID(id, key);
    }
    protected Date getDateByID(String id, String key, boolean objectID) {
        return (Date) getValueByID(id, key,objectID);
    }

    protected int getIntByID(String id, String key) {
        return (int) getValueByID(id, key);
    }

    protected int getIntByID(String id, String key, boolean objectID) {
        return (int) getValueByID(id, key,objectID);
    }

    protected void setValueByID(String id, String key, Object value) {
        getCollection().update(new BasicDBObject("_id", id), new BasicDBObject(key, value));
    }

    protected void setValueByID(String id, String key, Object value, boolean objectID) {
        if(objectID){
            getCollection().update(new BasicDBObject("_id", new ObjectId(id)), new BasicDBObject(key, value));
        }else{
            getCollection().update(new BasicDBObject("_id", id), new BasicDBObject(key, value));
        }
    }

    protected void pushValueByID(String id, String listKey, Object value) {
        pushValueByID(id,listKey,value,false);
    }

    protected void pushValueByID(String id, String listKey, Object value, boolean objectID) {
        BasicDBObject dbObject = (BasicDBObject) getObjectByID(id,objectID);
        BasicDBList dbList =  (BasicDBList) dbObject.get(listKey);
        dbList.add(value);
        dbObject.append(listKey, dbList);
        if(objectID){
            getCollection().update(new BasicDBObject("_id", new ObjectId(id)), dbObject);
        }else{
            getCollection().update(new BasicDBObject("_id", id), dbObject);
        }
    }

    protected void removeValueByID(String id, String listKey, String index) {
        getCollection().update(new BasicDBObject("_id", id), new BasicDBObject("$pull", new BasicDBObject(listKey, new BasicDBObject("_id", index))));
    }

    protected void pushDBObjectByID(String id, String listKey, BasicDBObject value) {
        pushValueByID(id,listKey, value);
    }
    protected void pushDBObjectByID(String id, String listKey, BasicDBObject value, boolean objectID) {
        pushValueByID(id,listKey, value,objectID);
    }

    protected void pushStringByID(String id, String listKey, String value) {
        pushValueByID(id,listKey, value);
    }

    protected void pushStringByID(String id, String listKey, String value, boolean objectID) {
        pushValueByID(id,listKey, value,objectID);
    }

    protected void setStringByID(String id, String key, String value) {
        setValueByID(id, key, value);
    }

    protected void setStringByID(String id, String key, String value, boolean objectID) {
        setValueByID(id, key, value, objectID);
    }

    protected void setIntByID(String id, String key, int value) {
        setValueByID(id, key, value);
    }

    protected void setIntByID(String id, String key, int value, boolean objectID) {
        setValueByID(id, key, value, objectID);
    }

    protected BasicDBObject objectIDToID(BasicDBObject object) {
        ((BasicDBObject) object).replace("_id",((ObjectId) object.get("_id")).toString());
        return object;
    }

}

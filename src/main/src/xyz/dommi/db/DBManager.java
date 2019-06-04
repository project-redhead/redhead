package xyz.dommi.db;

import com.mongodb.*;

import java.util.Date;

public abstract class DBManager {
    protected DB db;
    protected String tablename;

    public DBManager(DB db, String tablename) {
        this.db = db;
        this.tablename = tablename;
        init();
    }

    protected void init(){
        if(getCollection()==null){
            db.createCollection(tablename,null);
        }
    }

    protected DBCollection getCollection(){
        return db.getCollection(tablename);
    }
    protected DBObject getObjectByID(String id){
        return getCollection().findOne(new BasicDBObject("_id", id));
    }
    protected Object getValueByID(String id, String key){
        return getObjectByID(id).get(key);
    }
    protected String getStringByID(String id, String key){
        return (String) getValueByID(id,key);
    }
    protected String[] getStringArrayByID(String id, String key){
        return (String[]) getValueByID(id,key);
    }
    protected Date getDateByID(String id, String key){
        return (Date) getValueByID(id,key);
    }
    protected int getIntByID(String id, String key){
        return Integer.valueOf(getStringByID(id,key));
    }

    protected void setValueByID(String id, String key, Object value){
        getCollection().update(new BasicDBObject("_id", id),new BasicDBObject(key, value));
    }

    protected void pushValueByID(String id, Object value){
        getCollection().update(new BasicDBObject("_id", id),new BasicDBObject("$push",value));
    }

    protected void pushDBObjectByID(String id, BasicDBObject value){
        pushValueByID(id,value);
    }

    protected void pushStringByID(String id, String value){
        pushValueByID(id,value);
    }

    protected void pushStringByID(String id, int value){
        pushValueByID(id,value);
    }

    protected void setStringByID(String id, String key, String value){
        setValueByID(id,key,value);
    }

    protected void setIntByID(String id, String key, int value){
        setValueByID(id,key,value);
    }


}

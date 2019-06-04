package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.Date;

public class UserDB extends DBManager{

    public UserDB(DB db) {
        super(db, "Users");
    }

    public String getName(String id){
        return getStringByID(id,"name");
    }
    public String getEmail(String id){
        return getStringByID(id,"email");
    }
    public String getRoleID(String id){
        return getStringByID(id,"roleID");
    }
    public int getPoints(String id){
        return getIntByID(id,"roleID");
    }

    public void setName(String id, String name){
        setStringByID(id,"name", name);
    }
    public void setEmail(String id, String email){
        setStringByID(id,"email", email);
    }
    public void setRoleID(String id, String roleID){
        setStringByID(id,"roleID", roleID);
    }
    public void setPoints(String id, int points){
        setIntByID(id,"points", points);
    }

    public void addBet(String id, String gameid, int amount, int option){
        BasicDBObject bet = new BasicDBObject()
                .append("gameid",gameid)
                .append("date", new Date())
                .append("amount",amount)
                .append("option",option);
        pushDBObjectByID(id,bet);
    }

    public boolean userExists(String id){
        return getObjectByID(id) != null;
    }

    public void createUser(String id,String name, String email) {
        if(!userExists(id)){
            DBObject user = new BasicDBObject("_id", id)
                                .append("name", name)
                                .append("email", email)
                                .append("bets", new BasicDBList())
                                .append("points",0)
                                .append("roleid","user");
            getCollection().insert(user);
        }

    }

}

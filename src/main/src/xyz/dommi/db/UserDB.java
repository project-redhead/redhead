package xyz.dommi.db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

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
    public List<DBObject> getUsers(){
       return getDBObjectListByCollection();
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

    public boolean addBet(String id, String gameid, int amount, int option){
        BetGameDB gameDB = new BetGameDB(db);
        if(getPoints(id)>= amount){
            setPoints(id,getPoints(id)-amount);
            gameDB.addBet(gameid,id,amount,option);
            return true;
        }
        return false;
    }
    public boolean addGame(String id, String description, List<String> options){
        BetGameDB gameDB = new BetGameDB(db);
        gameDB.createBetGame(description,id, options);
        return true;
    }

    public boolean userExists(String id){
        return getObjectByID(id) != null;
    }

    public void createUser(String id,String name, String email) {
        if(!userExists(id)){
            DBObject user = new BasicDBObject("_id", id)
                                .append("name", name)
                                .append("email", email)
                                .append("points",0)
                                .append("roleid","user");
            getCollection().insert(user);
        }

    }

}

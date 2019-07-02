package xyz.dommi.beans;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

public class UserBean {

    private String id;
    private String name;
    private String email;
    private int points;
    private String role;

    public UserBean(){
    }

    public UserBean(String id){
        setId(id);
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            name = userDB.getName(id);
            email = userDB.getEmail(id);
            points = userDB.getPoints(id);
            role = userDB.getRoleID(id);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setName(getId(),name);
        }
    }

    public void setEmail(String email) {
        this.email = email;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setEmail(getId(),email);
        }
    }

    public void setPoints(int points) {
        this.points = points;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setPoints(getId(),points);
        }
    }

    public void setRole(String role) {
        this.role = role;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setRoleID(getId(),role);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getPoints() {
        return this.points;
    }

    public String getRole() {
        return this.role;
    }
}

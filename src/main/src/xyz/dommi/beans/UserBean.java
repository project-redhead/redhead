package xyz.dommi.beans;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

/**
 * Manages the Users
 */
public class UserBean {

    private String id;
    private String name;
    private String email;
    private int points;
    private String role;

    public UserBean(){
    }

    /**
     * @param id UserID
     */
    public UserBean(String id){
        setId(id);
    }

    public void setId(String id) {
        this.id = id;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            name = userDB.getName(id);
            email = userDB.getEmail(id);
            points = userDB.getPoints(id);
            role = userDB.getRoleID(id);
        }
    }

    /**
     * @param name Username
     */
    public void setName(String name) {
        this.name = name;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setName(getId(),name);
        }
    }

    /**
     * @param email registered Email-Address of the User
     */
    public void setEmail(String email) {
        this.email = email;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setEmail(getId(),email);
        }
    }

    /**
     * @param points actual available Points of the User
     */
    public void setPoints(int points) {
        this.points = points;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            userDB.setPoints(getId(),points);
        }
    }

    /**
     * @param role User-Role
     */
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

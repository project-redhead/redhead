package xyz.dommi.beans;

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
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            name = userDB.getName(id);
            email = userDB.getEmail(id);
            points = userDB.getPoints(id);
            role = userDB.getRoleID(id);
            dbConnection.close();
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            userDB.setName(getId(),name);
            dbConnection.close();
        }
    }

    public void setEmail(String email) {
        this.email = email;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            userDB.setEmail(getId(),email);
            dbConnection.close();
        }
    }

    public void setPoints(int points) {
        this.points = points;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            userDB.setPoints(getId(),points);
            dbConnection.close();
        }
    }

    public void setRole(String role) {
        this.role = role;
        if(getId() != null && !getId().equalsIgnoreCase("")){
            DBConnection dbConnection = new DBConnection();
            dbConnection.connect();
            UserDB userDB = new UserDB(dbConnection.getConnection());
            userDB.setRoleID(getId(),role);
            dbConnection.close();
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

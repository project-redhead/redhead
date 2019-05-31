package xyz.dommi.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB extends DBManager{

    public UserDB(Connection con) {
        super(con, "Users");
    }

    public String getName(String id){
        return getString("id", id,"name");
    }
    public String getEmail(String id){
        return getString("id", id,"email");
    }
    public String getRoleID(String id){
        return getString("id", id,"roleid");
    }
    public int getPoints(String id){
        return getInt("id", id,"points");
    }

    public void setName(String id, String name){
        update("id", id,"name",name);
    }
    public void setEmail(String id, String email){
        update("id", id,"email",email);
    }
    public void setRoleID(String id, String roleID){
        update("id", id,"roleid",roleID);
    }
    public void setPoints(String id, int points){
        update("id", id,"points",""+points);
    }



    public boolean userExists(String id){
        try {
            Statement statement = con.createStatement();
            String valueStr = "";
            ResultSet rs = statement.executeQuery("select id from " + tablename);
            while (rs.next()){
                if(rs.getString("id").equalsIgnoreCase(id)){
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void createUser(String id,String name, String email) {
        if(!userExists(id)){
            String[] keys = {"id","name","email","points","roleid"};
            String[] values = {id,name,email,"0","0"};
            insert(keys,values);
        }

    }

    @Override
    protected void init() {
        try {
            Statement stmt = con.createStatement();
            System.out.println("Init Table : " + tablename + "..");
            String strUpdate = "CREATE TABLE IF NOT EXISTS " + tablename +
                    "(`id` varchar(18) not null , " +
                    "`name` varchar(45) not null, " +
                    "`email` varchar(45) not null, " +
                    "`points` int not null, " +
                    "`roleid` varchar(18) not null, " +
                    "PRIMARY KEY (`id`));";

            stmt.executeUpdate(strUpdate);
            System.out.println("Init Table : " + tablename + " finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

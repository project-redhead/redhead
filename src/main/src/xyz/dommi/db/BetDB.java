package xyz.dommi.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BetDB extends DBManager{

    public BetDB(Connection con){
        super(con, "Bets");
    }

    public int createBet(String gameid, String creatorId,int amount, String optionid) {
        String[] keys = {"id","gameid","date","creator","amount","option"};
        int id = getLastId("id");
        id++;
        String[] values = {""+id,gameid,getCurrentDateString(),creatorId,""+amount,optionid};
        insert(keys,values);
        return id;
    }
    @Override
    protected void init() {
        try {
            Statement stmt = con.createStatement();
            System.out.println("Init Table : " + tablename + "..");
            String strUpdate = "CREATE TABLE IF NOT EXISTS " + tablename +
                    "(`id` int not null , " +
                    "`gameid` int not null , " +
                    "`date` datetime not null, " +
                    "`creator` varchar(18) not null, " +
                    "`amount` int not null, " +
                    "`option` int(1) not null, " +
                    "PRIMARY KEY (`id`));";

            stmt.executeUpdate(strUpdate);
            System.out.println("Init Table : " + tablename + " finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

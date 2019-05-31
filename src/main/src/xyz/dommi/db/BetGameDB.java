package xyz.dommi.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class BetGameDB extends DBManager {

    public BetGameDB(Connection con) {
        super(con, "BetGames");
    }

    public String getDescription(String id) {
        return getString("id", id, "description");
    }

    public String getDate(String id) {
        return getString("id", id, "date");
    }

    public String getCreator(String id) {
        return getString("id", id, "description");
    }

    public List<String> getOptions(String id) {
        String[] labels = {"OptionA", "OptionB", "OptionC", "OptionD", "OptionE"};
        List<String> options = getStrings(tablename, "Options", tablename + ".optionsId", "Options.id", labels);
        options.removeAll(Collections.singleton(null));
        return options;
    }

    public int getAnswer(String id) {
        return getInt("id", id, "answer");
    }
    public int createBetGame(String description, String creatorId, String[] options) {
        int optionsid = new OptionsDB(con).createOptions(options,false);
        return createBetGame(description, creatorId, ""+new OptionsDB(con).createOptions(options,false));
    }
    public int createBetGame(String description, String creatorId, String optionsid) {
        String[] keys = {"id","description","date","creator","optionsId","answer"};
        int id = getLastId("id");
        id++;
        String[] values = {""+id,description,getCurrentDateString(),creatorId,optionsid,null};
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
                    "`description` text not null, " +
                    "`date` datetime not null, " +
                    "`creator` varchar(18) not null, " +
                    "`optionsId` int not null, " +
                    "`answer` int(1) null, " +
                    "PRIMARY KEY (`id`));";

            stmt.executeUpdate(strUpdate);
            System.out.println("Init Table : " + tablename + " finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

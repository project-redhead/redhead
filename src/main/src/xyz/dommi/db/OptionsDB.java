package xyz.dommi.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OptionsDB extends DBManager{

    public OptionsDB(Connection con){
        super(con, "Options");
    }

    public int createOptions(String[] options,boolean isDefault) {
        String[] keys = {"id","OptionA","OptionB","OptionC","OptionD","OptionE","isDefault"};
        int id = getLastId("id");
        id++;
        String[] values = {
                ""+id,
                options[0],
                options[1],
                (options.length >= 3)? options[2] : null,
                (options.length >= 4)? options[3] : null,
                (options.length >= 5)? options[4] : null,
                (isDefault)? "1" : "0"
        };
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
                    "`OptionA` text not null, " +
                    "`OptionB` text not null, " +
                    "`OptionC` text null, " +
                    "`OptionD` text null, " +
                    "`OptionE` text null, " +
                    "`isDefault` int(1) not null, " +
                    "PRIMARY KEY (`id`));";

            stmt.executeUpdate(strUpdate);
            System.out.println("Init Table : " + tablename + " finished");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

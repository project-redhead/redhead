package xyz.dommi.db;

import java.sql.*;
import java.util.Properties;

import org.mariadb.jdbc.Driver;

public class DBConnection {
    private Connection con;

    public DBConnection() {

    }

    public Connection getConnection() {
        if (con != null) {
            return con;
        } else {
            connect();
            return con;
        }
    }

    public void connect() {
        try {
            System.out.println("Connecting..");
            Driver driver = new Driver();
            con = driver.connect("jdbc:mariadb://localhost:3306/DB?user=root&password=D4(=j2aAz#AjB?hwG$JBGCZ={FhG2x{xNnkhLa:3wX)zc8eG?MLpADEPXasL9BN2", new Properties());
            //con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=D4(=j2aAz#AjB?hwG$JBGCZ={FhG2x{xNnkhLa:3wX)zc8eG?MLpADEPXasL9BN2");

            System.out.println("Connected");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }
    public void close() {
        try {
            System.out.println("Closing..");
           con.close();
            System.out.println("Closed");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
    }


}

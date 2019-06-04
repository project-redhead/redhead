package xyz.dommi.db;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.Properties;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mariadb.jdbc.Driver;

public class DBConnection {
    private DB db;

    public DBConnection() {

    }

    public DB connect() {
        if(db == null){
            try {
                System.out.println("Connecting..");
                Driver driver = new Driver();
                MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
                db = mongoClient.getDB("admin");
                //con = driver.connect("jdbc:mariadb://localhost:3306/DB?user=root&password=D4(=j2aAz#AjB?hwG$JBGCZ={FhG2x{xNnkhLa:3wX)zc8eG?MLpADEPXasL9BN2", new Properties());
                //con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=D4(=j2aAz#AjB?hwG$JBGCZ={FhG2x{xNnkhLa:3wX)zc8eG?MLpADEPXasL9BN2");

                System.out.println("Connected");
                return db;
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        }
        return db;
    }


}

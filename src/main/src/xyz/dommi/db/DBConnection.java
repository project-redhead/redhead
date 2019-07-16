package xyz.dommi.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import xyz.dommi.common.EnvironmentConfig;

public class DBConnection {
    private DB db;
    private static DBConnection instance;

    public DBConnection() {

    }

    public DB connect() {
        if(db == null){
            try {
                System.out.println("Connecting..");
                MongoClient mongoClient = new MongoClient(new MongoClientURI(EnvironmentConfig.getDatabaseConnectionString()));
                db = mongoClient.getDB("admin");
                System.out.println("Connected");
                return db;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return db;
    }

    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }

        return  instance;
    }

}

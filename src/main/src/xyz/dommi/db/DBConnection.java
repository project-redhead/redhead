package xyz.dommi.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import xyz.dommi.common.EnvironmentConfig;

/**
 * Creates the connection to the DB
 */
public class DBConnection {
    private DB db;
    private static DBConnection instance;

    public DBConnection() {

    }

    /**
     * @return the DB or throws an exception if there is a problem with the connection
     */
    public DB connect() {
        if(db == null){
            try {
                System.out.println("Connecting..");
                MongoClient mongoClient = new MongoClient(new MongoClientURI(EnvironmentConfig.getDatabaseConnectionString()));
                db = mongoClient.getDB("redhead");
                System.out.println("Connected");
                return db;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return db;
    }

    /**
     * @return new connection to the DB
     */
    public static DBConnection getInstance(){
        if(instance == null){
            instance = new DBConnection();
        }

        return  instance;
    }

}

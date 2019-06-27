package xyz.dommi.db;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DBConnection {
    private DB db;

    public DBConnection() {

    }

    public DB connect() {
        if(db == null){
            try {
                System.out.println("Connecting..");
                MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://redhead.ginomessmer.me:27017"));
                db = mongoClient.getDB("admin");
                System.out.println("Connected");
                return db;
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        }
        return db;
    }


}

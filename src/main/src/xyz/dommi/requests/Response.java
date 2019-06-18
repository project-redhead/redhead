package xyz.dommi.requests;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Response {
    private ResponseType type;
    private Object value;

    public Response(ResponseType type, String value){
        this.type = type;
        this.value = value;
    }
    public Response(ResponseType type, DBObject value){
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString(){
        BasicDBObject object = new BasicDBObject();
        object.append("status",type.toString());
        object.append("value",value);
        return JSON.serialize(object);
    }

}

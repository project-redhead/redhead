package xyz.dommi.requests;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


/**
 * Answer to the Client
 */
public class Response {
    private ResponseType type;
    private Object value;

    /**
     * @param type Type of the Response
     * @param value Value of the Response
     */
    public Response(ResponseType type, String value){
        this.type = type;
        this.value = value;
    }

    /**
     * @param type Type of the Response
     * @param value Value of the Response
     */
    public Response(ResponseType type, DBObject value){
        this.type = type;
        this.value = value;
    }

    /**
     * @return serialized JSON-Object
     */
    @Override
    public String toString(){
        BasicDBObject object = new BasicDBObject();
        object.append("status",type.toString());
        object.append("value",value);
        return JSON.serialize(object);
    }

}

package xyz.dommi.requests.json;

import com.mongodb.DBObject;
import xyz.dommi.common.MensaAPI;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class GetMealToday extends JsonRequest{

    public GetMealToday(RequestManager manager) {
        super("GetMealToday", manager);
    }

    @Override
    public Response handleRequest(HttpServletRequest request) {
        MensaAPI api = new MensaAPI(33);
        DBObject object = api.getMeal();
        if(object == null){
            return new Response(ResponseType.ERROR,"404 Not Found!");
        }
        return new Response(ResponseType.OK, object);
    }
}

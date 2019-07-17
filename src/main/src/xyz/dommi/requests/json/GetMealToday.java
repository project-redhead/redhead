package xyz.dommi.requests.json;

import com.mongodb.DBObject;
import xyz.dommi.common.MensaAPI;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Requests the Meal for the actual day
 */
public class GetMealToday extends JsonRequest{

    /**
     * @param manager Manger that contains this Request
     */
    public GetMealToday(RequestManager manager) {
        super("GetMealToday", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return meal of the actual day
     */
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

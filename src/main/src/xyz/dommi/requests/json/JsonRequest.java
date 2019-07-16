package xyz.dommi.requests.json;

import xyz.dommi.requests.Request;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * Superclass of the JSON-Requests
 */
public abstract class JsonRequest extends Request {

    /**
     * @param type Type of the JSON-Request (i.e. BetInfo, ClaimReward)
     * @param manager Manger that contains this Request
     */
    public JsonRequest(String type, RequestManager manager){
        super(type);
        manager.addJsonRequest(this);
    }

    public abstract Response handleRequest(HttpServletRequest request);

}

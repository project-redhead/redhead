package xyz.dommi.requests.json;

import xyz.dommi.requests.Request;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;

import javax.servlet.http.HttpServletRequest;

public abstract class JsonRequest extends Request {

    public JsonRequest(String type, RequestManager manager){
        super(type);
        manager.addJsonRequest(this);
    }

    public abstract Response handleRequest(HttpServletRequest request);

}

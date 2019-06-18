package xyz.dommi.requests;

import javax.servlet.http.HttpServletRequest;

public abstract class Request {
    protected String type;

    public Request(String type, RequestManager manager){
        this.type = type;
        manager.addRequest(this);
    }

    public abstract Response handleRequest(HttpServletRequest request);

    public String getType(){
        return type;
    }
}

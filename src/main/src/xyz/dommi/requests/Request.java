package xyz.dommi.requests;

import javax.servlet.http.HttpServletRequest;

public abstract class Request {
    protected String type;
    public Request(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
}

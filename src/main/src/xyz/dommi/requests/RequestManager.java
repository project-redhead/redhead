package xyz.dommi.requests;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    private List<Request> requests;

    public RequestManager(){
        init();
    }

    private void init(){
        requests = new ArrayList<>();
        new UserInfo(this);
    }

    public void addRequest(Request req){
        requests.add(req);
    }

    public Response handleRequest(HttpServletRequest request){
        for(Request req : requests){
            String type = request.getParameter("type");
            if(req.getType().equalsIgnoreCase(type)) {
                return req.handleRequest(request);
            }
        }
        return new Response(ResponseType.ERROR, "Type not found: " + request.getParameter("type"));
    }

}

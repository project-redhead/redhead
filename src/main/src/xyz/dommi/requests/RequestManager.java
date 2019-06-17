package xyz.dommi.requests;

import javax.servlet.http.HttpServletRequest;
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
        new CreateGame(this);
        new CreateBet(this);
        new GameList(this);
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

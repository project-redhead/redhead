package xyz.dommi.requests;

import xyz.dommi.requests.bean.BeanRequest;
import xyz.dommi.requests.bean.BetBeanRequest;
import xyz.dommi.requests.bean.BetGameBeanRequest;
import xyz.dommi.requests.bean.UserBeanRequest;
import xyz.dommi.requests.json.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    private List<JsonRequest> jsonRequests;
    private List<BeanRequest> beanRequests;

    public RequestManager(){
        init();
    }

    private void init(){
        jsonRequests = new ArrayList<>();
        beanRequests = new ArrayList<>();

        new UserInfo(this);
        new CreateGame(this);
        new CreateBet(this);
        new GameList(this);
        new GameInfo(this);
        new BetInfo(this);
        new SetAnswer(this);

        new BetBeanRequest(this);
        new BetGameBeanRequest(this);
        new UserBeanRequest(this);
    }

    public void addJsonRequest(JsonRequest req){
        jsonRequests.add(req);
    }
    public void addBeanRequest(BeanRequest req){
        beanRequests.add(req);
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        switch (getFormat(request)){
            case BEAN:
                handleBeanRequest(request,response);
                break;
            case JSON:
                response.getWriter().append(handleJSONRequest(request).toString());
                break;
        }

    }

    private RequestFormat getFormat(HttpServletRequest request){
        String format = request.getParameter("format");
        if(format == null ){
            return  RequestFormat.JSON;
        }
        if(format.equalsIgnoreCase("bean")){
            return  RequestFormat.BEAN;
        }

        return  RequestFormat.JSON;
    }

    private Response handleJSONRequest(HttpServletRequest request){
        for(JsonRequest req : jsonRequests){
            String type = request.getParameter("type");
            if(req.getType().equalsIgnoreCase(type)) {
                return req.handleRequest(request);
            }
        }
        return new Response(ResponseType.ERROR, "Type not found: " + request.getParameter("type"));
    }
    private void handleBeanRequest(HttpServletRequest request, HttpServletResponse response){
        for(BeanRequest req : beanRequests){
            String type = request.getParameter("type");
            if(req.getType().equalsIgnoreCase(type)) {
                req.handleRequest(request, response);
                return;
            }
        }
    }

}

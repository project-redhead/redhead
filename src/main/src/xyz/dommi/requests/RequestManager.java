package xyz.dommi.requests;

import xyz.dommi.requests.bean.*;
import xyz.dommi.requests.json.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manges every Request
 */
public class RequestManager {

    private List<JsonRequest> jsonRequests;
    private List<BeanRequest> beanRequests;

    public RequestManager(){
        init();
    }

    /**
     * Creates the correct Request
     */
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
        new SuggestionInfo(this);
        new CreateSuggestion(this);
        new SetSuggestionRead(this);
        new GetMealToday(this);
        new ClaimReward(this);

        new BetBeanRequest(this);
        new BetGameBeanRequest(this);
        new UserBeanRequest(this);
        new SuggestionBeanRequest(this);
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
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(handleJSONRequest(request).toString());
                break;
        }

    }

    /**
     * @param request HttpServletrequest
     * @return Format of the request
     */
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

    /**
     * @param request HttpServletRequest
     * @return
     */
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

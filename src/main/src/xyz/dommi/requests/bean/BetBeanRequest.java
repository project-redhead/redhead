package xyz.dommi.requests.bean;

import xyz.dommi.beans.BetBean;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Requests a new BetBean
 */
public class BetBeanRequest extends  BeanRequest{

    /**
     * @param manager Manger that contains this Request
     */
    public BetBeanRequest(RequestManager manager) {
        super("BetBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String gameId = request.getParameter("gameId");

        if((id != null && !id.equals("")) && (gameId != null && !gameId.equals(""))){
            request.setAttribute("bean", new BetBean(gameId,id));
        }
    }
}

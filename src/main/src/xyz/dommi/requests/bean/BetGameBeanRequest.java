package xyz.dommi.requests.bean;

import xyz.dommi.beans.BetGameBean;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BetGameBeanRequest extends BeanRequest {

    public BetGameBeanRequest(RequestManager manager) {
        super("BetGameBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");

        request.setAttribute("bean", new BetGameBean(id, userId));
    }
}

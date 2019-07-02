package xyz.dommi.requests.bean;

import xyz.dommi.beans.BetGameBean;
import xyz.dommi.beans.SuggestionBean;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuggestionBeanRequest extends BeanRequest{


    public SuggestionBeanRequest(RequestManager manager) {
        super("SuggestionBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        request.setAttribute("bean", new SuggestionBean(id));
    }
}

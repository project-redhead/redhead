package xyz.dommi.requests.bean;

import xyz.dommi.beans.BetGameBean;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Requests a new BetGameBean
 */
public class BetGameBeanRequest extends BeanRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public BetGameBeanRequest(RequestManager manager) {
        super("BetGameBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String userId = HttpUtils.getUserIdFromJwt(request);

        request.setAttribute("bean", new BetGameBean(id, userId));
    }
}

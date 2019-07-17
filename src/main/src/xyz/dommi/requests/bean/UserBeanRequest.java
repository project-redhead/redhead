package xyz.dommi.requests.bean;

import xyz.dommi.beans.UserBean;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Requests a new UserBean
 */
public class UserBeanRequest extends  BeanRequest{

    /**
     * @param manager Manger that contains this Request
     */
    public UserBeanRequest(RequestManager manager) {
        super("UserBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = HttpUtils.getUserIdFromJwt(request);

        if(id != null && !id.equals("")){
            request.setAttribute("bean", new UserBean(id));
        }
    }
}

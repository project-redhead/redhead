package xyz.dommi.requests.bean;

import xyz.dommi.beans.UserBean;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserBeanRequest extends  BeanRequest{

    public UserBeanRequest(RequestManager manager) {
        super("UserBean", manager);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        if(id != null && !id.equals("")){
            request.setAttribute("bean", new UserBean(id));
        }
    }
}

package xyz.dommi.requests.bean;

import xyz.dommi.requests.Request;
import xyz.dommi.requests.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Superclass of the Bean-Requests
 */
public abstract class BeanRequest extends Request {

    /**
     * @param type Type of the Bean-Request (i.e. BetBean, UserBean)
     * @param manager Manger that contains this Request
     */
    public BeanRequest(String type, RequestManager manager) {
        super(type);
        manager.addBeanRequest(this);
    }

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response);
}

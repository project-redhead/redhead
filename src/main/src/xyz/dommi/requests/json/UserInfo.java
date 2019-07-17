package xyz.dommi.requests.json;

import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Requests a existing User
 */
public class UserInfo extends JsonRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public UserInfo(RequestManager manager){
        super("UserInfo", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return User as JSON-Object
     */
    public Response handleRequest(HttpServletRequest request){
        String id = request.getParameter("id");
        if(id == null || id.equalsIgnoreCase("")) {
            id = HttpUtils.getUserIdFromJwt(request);
        }
        if(id != null && !id.equals("")){

            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);

            DBObject user = userDB.getUser(id);

            if(user == null){
                return new Response(ResponseType.ERROR,"User not found");
            }

            return new Response(ResponseType.OK, user);
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}

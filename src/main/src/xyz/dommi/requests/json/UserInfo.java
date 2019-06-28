package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class UserInfo extends JsonRequest {

    public UserInfo(RequestManager manager){
        super("UserInfo", manager);
    }
    public Response handleRequest(HttpServletRequest request){
        String id = HttpUtils.getUserIdFromJwt(request);

        if(id != null && !id.equals("")){
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            UserDB userDB = new UserDB(db);

            return new Response(ResponseType.OK, userDB.getUser(id));
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}

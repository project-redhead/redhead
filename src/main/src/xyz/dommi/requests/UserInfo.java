package xyz.dommi.requests;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;

import javax.servlet.http.HttpServletRequest;

public class UserInfo extends Request{

    public UserInfo(RequestManager manager){
        super("UserInfo", manager);
    }
    public Response handleRequest(HttpServletRequest request){
        String id = request.getParameter("id");

        if(id != null && !id.equals("")){
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            UserDB userDB = new UserDB(db);

            return new Response(ResponseType.OK, userDB.getUser(id));
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}

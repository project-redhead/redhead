package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Request the collection of a reward
 */
public class ClaimReward extends JsonRequest{

    /**
     * @param manager Manger that contains this Request
     */
    public ClaimReward(RequestManager manager) {
        super("ClaimReward", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return points the User get
     */
    @Override
    public Response handleRequest(HttpServletRequest request) {
        String id = HttpUtils.getUserIdFromJwt(request);
        System.out.println(id);

        if(id != null && !id.equals("")){
            DB db = DBConnection.getInstance().connect();
            UserDB userDB = new UserDB(db);
            return userDB.collectReward(id);
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}

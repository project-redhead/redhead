package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

/**
 * Requests creation of a new Answer
 */
public class SetAnswer extends JsonRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public SetAnswer(RequestManager manager) {
        super("SetAnswer", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return Answer as JSON-Object
     */
    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = HttpUtils.getUserIdFromJwt(request);
        String value = request.getParameter("value");

        if (userId != null && !userId.equals("")) {

            int ivalue = 0;
            try {
                ivalue = Integer.valueOf(value);
            }catch (Exception e){
                return new Response(ResponseType.ERROR, "Value not valid!");
            }


            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);
            return gameDB.setAnswer(id,userId,ivalue);

        }
        return new Response(ResponseType.ERROR, "UserId not valid!");
    }
}
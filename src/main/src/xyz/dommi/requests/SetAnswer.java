package xyz.dommi.requests;

import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;

import javax.servlet.http.HttpServletRequest;

public class SetAnswer extends Request {

    public SetAnswer(RequestManager manager) {
        super("SetAnswer", manager);
    }

    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String value = request.getParameter("value");

        if (userId != null && !userId.equals("")) {

            int ivalue = 0;
            try {
                ivalue = Integer.valueOf(value);
            }catch (Exception e){
                return new Response(ResponseType.ERROR, "Value not valid!");
            }

            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
            BetGameDB gameDB = new BetGameDB(db);
            return gameDB.setAnswer(id,userId,ivalue);

        }
        return new Response(ResponseType.ERROR, "UserId not valid!");
    }
}
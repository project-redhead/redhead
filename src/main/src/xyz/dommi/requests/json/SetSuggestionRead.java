package xyz.dommi.requests.json;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.DBManager;
import xyz.dommi.db.SuggestionDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class SetSuggestionRead extends JsonRequest{

    public SetSuggestionRead(RequestManager manager) {
        super("SetSuggestionRead", manager);
    }

    @Override
    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        boolean read = Boolean.valueOf(request.getParameter("read"));

        if (id != null && !id.equals("")) {
            DB db = DBConnection.getInstance().connect();
            SuggestionDB suggestionDB = new SuggestionDB(db);
            suggestionDB.setRead(id, read);
            return new Response(ResponseType.OK, "Read was set to: " + read);
        }
        return new Response(ResponseType.ERROR, "Id not valid");
    }
}

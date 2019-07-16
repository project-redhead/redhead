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

/**
 * Requests a existing Suggestion
 */
public class SuggestionInfo extends JsonRequest{

    /**
     * @param manager Manger that contains this Request
     */
    public SuggestionInfo(RequestManager manager) {
        super("SuggestionInfo", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return Suggestion as JSON-Object
     */
    @Override
    public Response handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");

        if(id != null && !id.equals("")){
            DB db = DBConnection.getInstance().connect();
            SuggestionDB suggestionDB = new SuggestionDB(db);
            DBObject suggestion = suggestionDB.getSuggestion(id);
            if(suggestion != null){
                return new Response(ResponseType.OK, DBManager.objectIDToID((BasicDBObject) suggestion));
            }
        }
        return new Response(ResponseType.ERROR,"Id not valid");
    }
}
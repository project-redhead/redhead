package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.SuggestionDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;

public class CreateSuggestion extends JsonRequest{

    public CreateSuggestion(RequestManager manager) {
        super("CreateSuggestion", manager);
    }

    @Override
    public Response handleRequest(HttpServletRequest request) {
        String content = request.getParameter("content");
        String creatorId = HttpUtils.getUserIdFromJwt(request);

        if (content == null || content.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "Content cannot be empty!");
        }
        if (creatorId == null || creatorId.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "CreatorId cannot be empty!");
        }
        try {
            DB db = DBConnection.getInstance().connect();
            SuggestionDB suggestionDB = new SuggestionDB(db);
            return suggestionDB.createSuggestion(creatorId, content);

        } catch (Exception e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }
}
package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Request the creation of a new BetGame
 */
public class CreateGame extends JsonRequest {

    /**
     * @param manager Manger that contains this Request
     */
    public CreateGame(RequestManager manager) {
        super("CreateGame", manager);
    }

    /**
     * @param request HttpServletRequest
     * @return id of the created Game
     */
    public Response handleRequest(HttpServletRequest request) {
        String description = request.getParameter("description");
        String creatorId = HttpUtils.getUserIdFromJwt(request);
        String options = request.getParameter("options");
        String timelimit = request.getParameter("timelimit");

        if (description == null || description.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "Description cannot be empty!");
        }
        if (creatorId == null || creatorId.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "CreatorId cannot be empty!");
        }
        if (options == null || options.equalsIgnoreCase("")) {
            return new Response(ResponseType.ERROR, "Options cannot be empty!");
        }
        try {
            DB db = DBConnection.getInstance().connect();
            BetGameDB gameDB = new BetGameDB(db);

            List<String> optionList = new ArrayList<>();
            for (String option : options.split(",")) {
                optionList.add(option);
            }

            if (timelimit != null && !timelimit.equals("")) {
                return gameDB.createBetGame(description, creatorId, optionList, new Date(Date.parse(timelimit)));
            } else {
                return gameDB.createBetGame(description, creatorId, optionList, null);
            }
        } catch (Exception e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }
}

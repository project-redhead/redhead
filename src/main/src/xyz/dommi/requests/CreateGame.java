package xyz.dommi.requests;

import com.mongodb.DB;
import com.mongodb.util.JSON;
import xyz.dommi.db.BetGameDB;
import xyz.dommi.db.DBConnection;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateGame extends Request {

    public CreateGame(RequestManager manager) {
        super("CreateGame", manager);
    }

    public Response handleRequest(HttpServletRequest request) {
        String description = request.getParameter("description");
        String creatorId = request.getParameter("creatorId");
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
            DBConnection dbConnection = new DBConnection();
            DB db = dbConnection.connect();
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

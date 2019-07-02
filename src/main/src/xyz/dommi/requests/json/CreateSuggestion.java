package xyz.dommi.requests.json;

import com.mongodb.DB;
import xyz.dommi.common.DiscordWebhook;
import xyz.dommi.common.HttpUtils;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.SuggestionDB;
import xyz.dommi.db.UserDB;
import xyz.dommi.requests.RequestManager;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

public class CreateSuggestion extends JsonRequest {

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
            UserDB userDB = new UserDB(db);

            DiscordWebhook webhook = new DiscordWebhook("https://discordapp.com/api/webhooks/588635822278967296/Yrlh5v3XucTZIm4utHO9o5faz-a1sVKlRp8IeJPxh1LngNCzKu7V_RfXmg9DzMuy-dJn");
            webhook.setContent("");
            webhook.setAvatarUrl("http://localhost:8080/");
            webhook.setUsername("Kummerkasten");
            webhook.setTts(false);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle("Problem:")
                    .setDescription(content)
                    .setColor(Color.RED)
                    .setAuthor(userDB.getName(creatorId), "http://localhost:8080/", "http://localhost:8080/")
                    .setUrl("http://localhost:8080/"));
            webhook.execute();


            return suggestionDB.createSuggestion(creatorId, content);

        } catch (Exception e) {
            return new Response(ResponseType.ERROR, e.getMessage());
        }
    }
}
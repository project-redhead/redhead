package xyz.dommi.beans;

import com.mongodb.DB;
import xyz.dommi.db.DBConnection;
import xyz.dommi.db.SuggestionDB;
import xyz.dommi.requests.Response;
import xyz.dommi.requests.ResponseType;

public class SuggestionBean {

    private String id;
    private String suggestion;

    public SuggestionBean(){

    }

    public SuggestionBean(String id){
        this.id = id;
        if(id != null && !id.equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            SuggestionDB suggestionDB = new SuggestionDB(db);
            suggestion = new Response(ResponseType.OK,suggestionDB.getSuggestion(id)).toString();
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSuggestion(String suggestion){
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        if(suggestion != null && !suggestion.equalsIgnoreCase("")) {
            return suggestion;
        }else if(id != null && !id.equalsIgnoreCase("")){
            DB db = DBConnection.getInstance().connect();
            SuggestionDB suggestionDB = new SuggestionDB(db);
            suggestion = new Response(ResponseType.OK,suggestionDB.getSuggestion(id)).toString();
            return suggestion;
        }
        return new Response(ResponseType.ERROR,"ID not found").toString();
    }

    public String getSuggestionlist(){
        DB db = DBConnection.getInstance().connect();
        SuggestionDB suggestionDB = new SuggestionDB(db);

        return new Response(ResponseType.OK, suggestionDB.getSuggestionList()).toString();
    }
}

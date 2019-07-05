package xyz.dommi.common;


import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MensaAPI {

    private int id = 33;

    public MensaAPI(int id){
        this.id = id;
    }

    public DBObject getMensa(){
        return request("/canteens/"+id);
    }

    public DBObject getMeal(){
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        String todayAsString = df.format(today);
        return request("/canteens/"+id+"/days/"+todayAsString+"/meals");

    }

    private DBObject request(String parameter){
        try {

            String url = "https://openmensa.org/api/v2"+parameter;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "USER_AGENT");
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8 ));
            StringBuilder s = new StringBuilder();
            while (true){
                String line = reader.readLine();
                if(line == null || line.equalsIgnoreCase("")){
                    return (DBObject) JSON.parse(s.toString());
                }else{
                    s.append(line);
                }
            }
        }catch (Exception e){
            return null;
        }
    }


}

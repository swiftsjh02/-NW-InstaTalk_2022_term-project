package function;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class coinprice {



    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

        String wow=null;
        System.out.println("hi");
        JSONParser parser = new JSONParser();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.upbit.com/v1/ticker/?markets=KRW-BTC"))
                .header("accept", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        Object obj= parser.parse(response.body());
        JSONArray json = (JSONArray) obj;
        List<JSONObject> res= new ArrayList<JSONObject>();
        for(int i=0; i<json.size(); i++){
            JSONObject temp = (JSONObject) json.get(i);
            res.add(temp);
            wow= temp.get("trade_price").toString();
        }

        System.out.println(wow);




    }
}

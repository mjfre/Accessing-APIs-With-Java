import com.google.gson.Gson;
import pojo.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Request {

    Gson gson = new Gson();

    public static void main(String[] args) {
        try {
            new Request().getUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getUser() throws IOException {

        //The URL for the endpoint we want to access
        String requestURL = "https://jsonplaceholder.typicode.com/users/1";

        //Setup request
        URL url = new URL(requestURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //We want to GET the resource
        con.setRequestMethod("GET");

        //Read the response
        JsonReader repoReader = Json.createReader(con.getInputStream());
        JsonObject userJSON = ((JsonObject) repoReader.read());

        //Disconnect
        con.disconnect();

        //turn user to POJO
        User user = gson.fromJson(userJSON.toString(), User.class);

        //Proof that it works
        System.out.println("userJSON: " + userJSON);
        System.out.println("GET USER NAME FROM POJO: " + user.getName());
        System.out.println("GET USER CITY FROM POJO: " + user.getAddress().getCity());

    }


}

package fr.houseofcode.dap.commandLine.rma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilsServer {

    private final String USER_AGENT = "Mozilla/5.0";

    public String getNextEvent(String userKey) throws IOException {
        String event = callServer("/event/next", userKey);
        return event;
    }

    public String getLabels(String userKey) throws IOException {
        String label = callServer("/label/print", userKey);
        return label;
    }

    public String getNbUnreadEmail(String userKey) throws IOException {
        String nbEmail = callServer("/email/nbUnread", userKey);

        return nbEmail;
    }

    private String callServer(String url, String userKey) throws IOException {

        URL obj = new URL("http://localhost:8080" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080" + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        return response.toString();

    }

}

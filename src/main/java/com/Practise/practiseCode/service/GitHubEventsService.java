package com.Practise.practiseCode.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class GitHubEventsService {

    public void fetchAndDisplay(String username){
        String apiUrl = "https://api.github.com/users/" + username + "/events";
        System.out.println("Fetching data from: " + apiUrl); // Debug log

        String response = fetchData(apiUrl);

        if (response == null) {
            System.out.println("Failed to fetch data for user: " + username);
            return;
        }

        try {
            JsonArray events = JsonParser.parseString(response).getAsJsonArray();

            if (events.size() == 0) {
                System.out.println("No events found for user: " + username);
                return;
            }

            System.out.println("Found " + events.size() + " events for user: " + username);

            for (int i = 0; i < Math.min(events.size(), 5); i++) {
                JsonObject event = events.get(i).getAsJsonObject();

                String type = event.get("type").getAsString();
                String repo = event.getAsJsonObject("repo").get("name").getAsString();
                String actor = event.getAsJsonObject("actor").get("login").getAsString();
                String createdAt = event.get("created_at").getAsString();

                System.out.println("Event: " + type + " | Repo: " + repo + " | Actor: " + actor + " | Timestamp: " + createdAt);
            }
        } catch (Exception e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
            System.out.println("Response was: " + response);
        }
    }

    private String fetchData(String apiUrl){
        HttpURLConnection conn = null;
        try{
            System.out.println("=== DEBUG: Starting API call ===");
            System.out.println("URL: " + apiUrl);

            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Accept", "application/vnd.github+json");
//            conn.setRequestProperty("User-Agent", "GitHubEventsApp/1.0");
//            conn.setConnectTimeout(15000); // 15 second timeout
//            conn.setReadTimeout(15000);    // 15 second read timeout

            System.out.println("Connection established, getting response...");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Message: " + conn.getResponseMessage());

            if(responseCode != 200) {
                System.out.println("=== ERROR RESPONSE DETAILS ===");
                if (conn.getErrorStream() != null) {
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    StringBuilder errorMsg = new StringBuilder();
                    String line;
                    while ((line = errorReader.readLine()) != null) {
                        errorMsg.append(line).append("\n");
                    }
                    errorReader.close();
                    System.out.println("Error Body: " + errorMsg.toString());
                } else {
                    System.out.println("No error stream available");
                }
                return null;
            }

            System.out.println("Reading successful response...");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();

            System.out.println("Response length: " + sb.length() + " characters");
            System.out.println("=== DEBUG: API call completed successfully ===");
            return sb.toString();

        } catch(Exception e) {
            System.out.println("=== EXCEPTION DETAILS ===");
            System.out.println("Exception type: " + e.getClass().getSimpleName());
            System.out.println("Exception message: " + e.getMessage());
            System.out.println("Full stack trace:");
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
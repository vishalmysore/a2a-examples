package org.example.actions;

import com.t4a.annotations.Predict;
import com.t4a.api.AIAction;
import com.t4a.api.ActionType;
import com.t4a.api.JavaMethodAction;

import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Log
@Predict(actionName = "getTemperature",description = "get weather for city")
public class WeatherAction implements JavaMethodAction {
    public double getTemperature(String cityName) {
        double temperature = 0;
        String urlStr = "https://geocoding-api.open-meteo.com/v1/search?name="+cityName+"&count=1&language=en&format=json";
        String weatherURlStr = "https://api.open-meteo.com/v1/forecast?latitude=";
        try {
            StringBuilder response = getResponseFromURl(urlStr);

            // Parse JSON response
            JSONObject jsonObject  = new JSONObject(response.toString());

            // Extract latitude and longitude from the JSON response
            JSONArray resultsArray = jsonObject.getJSONArray("results");

            // Extract latitude and longitude from the first element of the "results" array
            if (resultsArray.length() > 0) {
                JSONObject result = resultsArray.getJSONObject(0);
                double latitude = result.getDouble("latitude");
                double longitude = result.getDouble("longitude");

                // Print latitude and longitude
                log.info("Latitude: " + latitude);
                log.info("Longitude: " + longitude);


                weatherURlStr = weatherURlStr+latitude+"&longitude="+longitude+"&current=temperature_2m";
                response = getResponseFromURl(weatherURlStr);

                log.info(response.toString());
                jsonObject = new JSONObject(response.toString());
                // Get the "current" object
                JSONObject currentObject = jsonObject.getJSONObject("current");

                // Extract the temperature value
                temperature = currentObject.getDouble("temperature_2m");

                // Print the temperature value
                log.info("Temperature: " + temperature + " °C");
            } else {
                log.info("No results found for the longitude and latidue for the city , city is invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temperature;
    }

    private static StringBuilder getResponseFromURl(String urlStr) throws IOException {
        // Create a URL object
        URL url = new URL(urlStr);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();
        log.info("Response Code: " + responseCode);

        // Read the response body
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // Disconnect the connection
        connection.disconnect();
        return response;
    }




}
package ru.example.weather;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {

    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";
    private static final String API_KEY_HEADER = "X-Yandex-Weather-Key";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String apiKey;

    public WeatherClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getForecast(double lat, double lon, int limit)
            throws IOException, InterruptedException {

        URI uri = URI.create(BASE_URL + "?lat=" + lat + "&lon=" + lon + "&limit=" + limit);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header(API_KEY_HEADER, apiKey)
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Weather API error: " + response.statusCode() + " " + response.body());
        }
        return response.body();
    }
}
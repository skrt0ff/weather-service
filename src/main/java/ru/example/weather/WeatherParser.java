package ru.example.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public int getCurrentTemp(String json) throws JsonProcessingException {
        JsonNode root = mapper.readTree(json);
        JsonNode temp = root.path("fact").path("temp");

        if (!temp.isNumber()) {
            throw new IllegalStateException("В ответе нет числового поля fact.temp");
        }
        return temp.asInt();
    }

    public double getAverageDayTemp(String json, int limit) throws JsonProcessingException {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit должен быть больше 0, получено: " + limit);
        }

        JsonNode root = mapper.readTree(json);
        JsonNode forecasts = root.path("forecasts");

        if (forecasts.size() < limit) {
            throw new IllegalStateException(
                    "В ответе " + forecasts.size() + " суток, а запрошено " + limit);
        }

        double sum = 0;
        for (int i = 0; i < limit; i++) {
            JsonNode tempAvg = forecasts.path(i).path("parts").path("day").path("temp_avg");

            if (!tempAvg.isNumber()) {
                throw new IllegalStateException(
                        "Нет parts.day.temp_avg в forecasts[" + i + "]");
            }
            sum += tempAvg.asDouble();
        }
        return sum / limit;
    }
}
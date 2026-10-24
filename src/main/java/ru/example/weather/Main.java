package ru.example.weather;

public class Main {

    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("YANDEX_WEATHER_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Не задана переменная окружения YANDEX_WEATHER_KEY");
            System.exit(1);
        }

        int limit = 3;

        WeatherClient client = new WeatherClient(apiKey);
        WeatherParser parser = new WeatherParser();

        String json = client.getForecast(55.75, 37.62, limit);

        System.out.println(json);
        System.out.println("Текущая температура: " + parser.getCurrentTemp(json));
        System.out.println("Средняя температура за " + limit + " сут.: "
                + parser.getAverageDayTemp(json, limit));
    }
}
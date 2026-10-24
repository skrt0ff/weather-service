# Weather Service

Консольное приложение на Java: получает прогноз погоды из API Яндекс Погоды, выводит полный JSON-ответ, текущую температуру (`fact.temp`) и среднюю дневную температуру за `limit` суток (`forecasts[*].parts.day.temp_avg`).

## Требования

- JDK 17
- Ключ API Яндекс Погоды: https://yandex.ru/pogoda/b2b/console/home

## Запуск

1. Получите ключ и задайте его в переменной окружения:

   ```bash
   export YANDEX_WEATHER_KEY=ваш_ключ
   ```

2. Запустите:

   ```bash
   ./gradlew run
   ```

   Или запустите класс `ru.example.weather.Main` из IDEA, задав переменную в Run Configuration.

Координаты (Москва) и `limit` задаются в `Main.java`.

## Структура

- `WeatherClient`: отправляет GET-запрос с заголовком `X-Yandex-Weather-Key`
- `WeatherParser`: достаёт `fact.temp` и считает среднюю температуру
- `Main`: точка входа, вывод результатов

## Пример вывода

```
{"now":...}   <- полный JSON
Текущая температура: 8
Средняя температура за 3 сут.: 10.666666666666666
```
package exercise.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import exercise.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeather(City city) {
        String url = "http://weather/api/v2/cities/" + city.getName();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> weather = new HashMap<>();
        try {
            weather = objectMapper.readValue(client.get(url), Map.class);
        } catch (Exception e) {
            throw  new RuntimeException();
        }
        return weather;
    }


    // END
}

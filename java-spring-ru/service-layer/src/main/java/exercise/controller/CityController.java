package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "cities/{id}")
    public Map<String, String> getCity(@PathVariable long id) {
         var city = cityRepository.findById(id)
                 .orElseThrow(() -> new CityNotFoundException("City not found"));
         return weatherService.getWeather(city);
    }
    @GetMapping(path = "/search")
    public List<Map<String, String>> getCitiesStartWith(
            @RequestParam(value = "name", required = false) String name) {
        List<Map<String, String>> result = new ArrayList<>();
        if (name == null) {
            var sortedCities = cityRepository.findAllByOrderByName();
            return sortedCities.stream()
                    .map(city -> weatherService.getWeather(city))
                    .collect(Collectors.toList());
        }
        var cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        return cities.stream()
                .map(city -> weatherService.getWeather(city))
                .filter(city -> city.containsKey("name") && city.containsKey("temperature"))
                .collect(Collectors.toList());
    }
    // END
}


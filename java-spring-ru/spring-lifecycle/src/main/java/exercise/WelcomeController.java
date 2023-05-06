package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Meal meal;
    @Autowired
    Daytime daytime;

    @GetMapping(path = "/daytime")
    public String root() {
        var timOfDay = daytime.getName();
        var mealName = meal.getMealForDaytime(timOfDay);
        return "It is " + timOfDay + " now. Enjoy your " + mealName;
    }
}
// END

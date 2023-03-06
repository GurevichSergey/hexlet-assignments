package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        if (apartments.isEmpty()) {
            return new ArrayList<>();
        }
        List<Home> result = apartments.stream()
                .sorted(Comparator.comparing(x -> x.getArea()))
                .collect(Collectors.toList());
        List<String> resultString = new ArrayList<>(count);
        for ( var i = 0; i < count; i++ ) {
            resultString.add(i, result.get(i).toString());
        }
        return resultString;
    }
}
// END

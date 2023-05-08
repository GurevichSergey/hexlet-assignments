package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {

        Map<String, Integer> result = new HashMap<>();

        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        minThread.start();
        maxThread.start();

        result.put("max", maxThread.getMaxNumber());
        result.put("min", minThread.getMinNumber());

        return result;
    }
    // END
}

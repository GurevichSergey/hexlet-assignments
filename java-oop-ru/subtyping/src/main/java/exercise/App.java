package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage keyValue) {
        Map<String, String> mapResult = new HashMap<>();
        for (var kv : keyValue.toMap().entrySet()) {
            mapResult.put(kv.getValue(), kv.getKey());
        }
        return new InMemoryKV(mapResult);
    }
}
// END

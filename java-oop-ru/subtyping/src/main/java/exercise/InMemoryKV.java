package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private final Map<String, String> keyValue;
    public InMemoryKV (Map<String, String> keyValue) {
        this.keyValue = new HashMap<>(keyValue);
    }
    public void set (String key, String value) {
        keyValue.put(key, value);
    }
    public void unset (String key) {
        keyValue.remove(key);
    }
    public String get (String key, String defaultValue) {
        if (keyValue.containsKey(key)) {
            return keyValue.get(key);
        }
        return defaultValue;
    }
    public Map<String, String> toMap() {
        return new HashMap<>(keyValue);
    }
}
// END

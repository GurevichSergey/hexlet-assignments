package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;


class AppTest {
    @Test
    void testSwapKV() {
        KeyValueStorage storage1 = new InMemoryKV(Map.of("key", "value"));
        storage1.set("key2", "value2");
        var storage = App.swapKeyValue(storage1);

        assertThat(storage.get("key3", "default")).isEqualTo("default");
        assertThat(storage.get("value", "")).isEqualTo("key");
        assertThat(storage.get("value2", "")).isEqualTo("key2");
    }

    @Test
    void testSwapKV2() {
        KeyValueStorage storage1 = new InMemoryKV(Map.of("foo", "bar", "bar", "zoo"));
        var storage = App.swapKeyValue(storage1);
        Map<String, String> expected = Map.of("bar", "foo", "zoo", "bar");
        assertThat(storage.toMap()).isEqualTo(expected);
    }
}

package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App{
    public static void save(Path path, Car car) {
        try {
            var file = Files.write(path, car.serialize().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Car extract(Path path) {
        try {
            var file = Files.readString(path);
            return Car.unserialize(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
